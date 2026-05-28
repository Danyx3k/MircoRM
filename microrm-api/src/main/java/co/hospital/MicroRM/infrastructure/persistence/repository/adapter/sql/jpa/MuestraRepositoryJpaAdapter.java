package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.NumeroLaboratorioDiarioFormatter;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
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
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
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
	private final EntityManager entityManager;
	private final Clock microRmClock;

	public MuestraRepositoryJpaAdapter(
			MuestraJpaRepository muestraJpaRepository,
			MuestraPacienteJpaRepository muestraPacienteJpaRepository,
			PacienteJpaRepository pacienteJpaRepository,
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			EstadoJpaRepository estadoJpaRepository,
			ColaboradorJpaRepository colaboradorJpaRepository,
			MicrolabCatalogResolver catalogResolver,
			EntityManager entityManager,
			Clock microRmClock) {
		this.muestraJpaRepository = muestraJpaRepository;
		this.muestraPacienteJpaRepository = muestraPacienteJpaRepository;
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.estadoJpaRepository = estadoJpaRepository;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.catalogResolver = catalogResolver;
		this.entityManager = entityManager;
		this.microRmClock = microRmClock;
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
				: catalogResolver.colaboradorCapturaMuestra();
		jpa.setColaborador(colaboradorJpaRepository.getReferenceById(colaboradorId));
		jpa.setFechaHoraToma(entity.getFechaHoraToma());
		jpa.setObservacionesLaboratorio(entity.getObservacionesLaboratorio());
		muestraJpaRepository.saveAndFlush(jpa);
		ensureNumeroLaboratorio(jpa);

		MuestraPacienteJPAEntity link = new MuestraPacienteJPAEntity();
		link.setIdMuestraPaciente(UUID.randomUUID());
		link.setMuestra(jpa);
		link.setPaciente(pacienteJpaRepository.getReferenceById(entity.getIdPaciente()));
		muestraPacienteJpaRepository.save(link);

		entity.setNumeroLaboratorio(jpa.getNumeroLaboratorio());
		return jpa.getIdMuestra();
	}

	private void ensureNumeroLaboratorio(MuestraJPAEntity jpa) {
		if (jpa.getNumeroLaboratorio() != null && !jpa.getNumeroLaboratorio().isBlank()) {
			return;
		}
		entityManager.refresh(jpa);
		if (jpa.getNumeroLaboratorio() != null && !jpa.getNumeroLaboratorio().isBlank()) {
			return;
		}
		LocalDate hoy = LocalDate.now(microRmClock);
		jpa.setNumeroLaboratorio(NumeroLaboratorioDiarioFormatter.format(hoy, 1));
		muestraJpaRepository.save(jpa);
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
