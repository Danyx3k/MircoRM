package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.query.ColaboradorContextService;
import co.hospital.MicroRM.infrastructure.persistence.query.MicrolabCatalogResolver;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.EstadoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.MuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.MuestraPacienteJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SitioAnatomicoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraPacienteJPAEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MuestraRepositoryJpaAdapter implements MuestraRepository {

	private final MuestraJpaRepository muestraJpaRepository;
	private final MuestraPacienteJpaRepository muestraPacienteJpaRepository;
	private final PacienteJpaRepository pacienteJpaRepository;
	private final SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository;
	private final TipoMuestraJpaRepository tipoMuestraJpaRepository;
	private final EstadoJpaRepository estadoJpaRepository;
	private final ColaboradorJpaRepository colaboradorJpaRepository;
	private final MicrolabCatalogResolver catalogResolver;
	private final ColaboradorContextService colaboradorContextService;

	public MuestraRepositoryJpaAdapter(
			MuestraJpaRepository muestraJpaRepository,
			MuestraPacienteJpaRepository muestraPacienteJpaRepository,
			PacienteJpaRepository pacienteJpaRepository,
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			EstadoJpaRepository estadoJpaRepository,
			ColaboradorJpaRepository colaboradorJpaRepository,
			MicrolabCatalogResolver catalogResolver,
			ColaboradorContextService colaboradorContextService) {
		this.muestraJpaRepository = muestraJpaRepository;
		this.muestraPacienteJpaRepository = muestraPacienteJpaRepository;
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.estadoJpaRepository = estadoJpaRepository;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.catalogResolver = catalogResolver;
		this.colaboradorContextService = colaboradorContextService;
	}

	@Override
	@Transactional
	public UUID create(MuestraEntity entity) {
		MuestraJPAEntity jpa = new MuestraJPAEntity();
		jpa.setIdMuestra(entity.getId());
		jpa.setTipoMuestra(tipoMuestraJpaRepository.getReferenceById(entity.getIdTipoMuestra()));
		jpa.setSitioAnatomico(sitioAnatomicoJpaRepository.getReferenceById(entity.getIdSitioAnatomico()));
		jpa.setEstado(estadoJpaRepository.getReferenceById(
				entity.getIdEstado() != null ? entity.getIdEstado() : catalogResolver.estadoRecibidaId()));
		UUID colaboradorId = entity.getIdColaborador() != null
				? entity.getIdColaborador()
				: colaboradorContextService.resolveColaboradorCapturaId();
		jpa.setColaborador(colaboradorJpaRepository.getReferenceById(colaboradorId));
		jpa.setFechaHoraToma(entity.getFechaHoraToma());
		jpa.setObservacionesLaboratorio(entity.getObservacionesLaboratorio());
		MuestraJPAEntity saved = muestraJpaRepository.saveAndFlush(jpa);
		saved = attachNumeroLaboratorioFromDatabase(saved.getIdMuestra());

		MuestraPacienteJPAEntity link = new MuestraPacienteJPAEntity();
		link.setIdMuestraPaciente(UUID.randomUUID());
		link.setMuestra(muestraJpaRepository.getReferenceById(saved.getIdMuestra()));
		link.setPaciente(pacienteJpaRepository.getReferenceById(entity.getIdPaciente()));
		muestraPacienteJpaRepository.save(link);

		entity.setNumeroLaboratorio(saved.getNumeroLaboratorio());
		return saved.getIdMuestra();
	}

	/**
	 * El trigger de BD asigna {@code numero_laboratorio} en el INSERT; Hibernate no siempre lo refleja
	 * en la entidad en memoria, por eso se lee con una consulta dedicada (evita duplicar ...001).
	 */
	private MuestraJPAEntity attachNumeroLaboratorioFromDatabase(UUID idMuestra) {
		String numero = muestraJpaRepository.findNumeroLaboratorioById(idMuestra)
				.filter(n -> n != null && !n.isBlank())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR));
		MuestraJPAEntity managed = muestraJpaRepository.findById(idMuestra)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_NO_ENCONTRADA));
		managed.setNumeroLaboratorio(numero);
		return managed;
	}

	@Override
	@Transactional
	public void updateCaptura(MuestraEntity entity) {
		MuestraJPAEntity jpa = muestraJpaRepository.findById(entity.getId())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_NO_ENCONTRADA));
		jpa.setTipoMuestra(tipoMuestraJpaRepository.getReferenceById(entity.getIdTipoMuestra()));
		jpa.setSitioAnatomico(sitioAnatomicoJpaRepository.getReferenceById(entity.getIdSitioAnatomico()));
		jpa.setFechaHoraToma(entity.getFechaHoraToma());
		muestraJpaRepository.save(jpa);
	}

	@Override
	public Optional<MuestraEntity> findById(UUID id) {
		return muestraJpaRepository.findById(id).map(this::toDomain);
	}

	@Override
	public boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio) {
		return muestraJpaRepository.existsByNumeroLaboratorioIgnoreCase(numeroLaboratorio);
	}

	@Override
	public boolean existsByNumeroLaboratorioIgnoreCaseExcludingId(String numeroLaboratorio, UUID excludeId) {
		return muestraJpaRepository.existsByNumeroLaboratorioIgnoreCaseAndIdMuestraNot(numeroLaboratorio, excludeId);
	}

	private MuestraEntity toDomain(MuestraJPAEntity jpa) {
		MuestraEntity e = new MuestraEntity();
		e.setId(jpa.getIdMuestra());
		e.setIdTipoMuestra(jpa.getTipoMuestra().getIdTipoMuestra());
		e.setTipoMuestraNombre(jpa.getTipoMuestra().getNombre());
		e.setIdSitioAnatomico(jpa.getSitioAnatomico().getIdSitioAnatomico());
		e.setSitioAnatomicoNombre(jpa.getSitioAnatomico().getNombre());
		e.setIdEstado(jpa.getEstado().getIdEstado());
		e.setEstadoNombre(jpa.getEstado().getNombre());
		e.setIdColaborador(jpa.getColaborador().getIdColaborador());
		e.setNumeroLaboratorio(jpa.getNumeroLaboratorio());
		e.setFechaRecepcion(jpa.getFechaRecepcion());
		e.setFechaHoraToma(jpa.getFechaHoraToma());
		e.setObservacionesLaboratorio(jpa.getObservacionesLaboratorio());
		e.setFechaCreacion(jpa.getFechaCreacion());
		e.setFechaActualizacion(jpa.getFechaActualizacion());
		return e;
	}
}
