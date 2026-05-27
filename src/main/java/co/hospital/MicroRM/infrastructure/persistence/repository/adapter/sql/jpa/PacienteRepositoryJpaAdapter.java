package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.PacienteJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.EpsJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SexoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoDocumentoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PacienteRepositoryJpaAdapter implements PacienteRepository {

	private final PacienteJpaRepository pacienteJpaRepository;
	private final TipoDocumentoJpaRepository tipoDocumentoJpaRepository;
	private final SexoJpaRepository sexoJpaRepository;
	private final EpsJpaRepository epsJpaRepository;

	public PacienteRepositoryJpaAdapter(
			PacienteJpaRepository pacienteJpaRepository,
			TipoDocumentoJpaRepository tipoDocumentoJpaRepository,
			SexoJpaRepository sexoJpaRepository,
			EpsJpaRepository epsJpaRepository) {
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.tipoDocumentoJpaRepository = tipoDocumentoJpaRepository;
		this.sexoJpaRepository = sexoJpaRepository;
		this.epsJpaRepository = epsJpaRepository;
	}

	@Override
	public void create(PacienteEntity entity) {
		PacienteJPAEntity jpa = new PacienteJPAEntity();
		jpa.setIdPaciente(entity.getId());
		applyScalars(jpa, entity);
		pacienteJpaRepository.save(jpa);
	}

	@Override
	public void update(PacienteEntity entity) {
		PacienteJPAEntity jpa = pacienteJpaRepository.findById(entity.getId())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
		applyScalars(jpa, entity);
		pacienteJpaRepository.save(jpa);
	}

	@Override
	public Optional<PacienteEntity> findById(UUID id) {
		return pacienteJpaRepository.findById(id).map(this::toDomain);
	}

	@Override
	public boolean existsByNumeroIdentificacionIgnoreCase(String numeroIdentificacion) {
		return pacienteJpaRepository.existsByNumeroIdentificacionIgnoreCase(numeroIdentificacion);
	}

	@Override
	public boolean existsByNumeroIdentificacionIgnoreCaseExcludingId(String numeroIdentificacion, UUID excludeId) {
		return pacienteJpaRepository.existsByNumeroIdentificacionIgnoreCaseAndIdPacienteNot(numeroIdentificacion, excludeId);
	}

	@Override
	public boolean existsById(UUID id) {
		return pacienteJpaRepository.existsById(id);
	}

	private void applyScalars(PacienteJPAEntity jpa, PacienteEntity entity) {
		jpa.setTipoDocumento(tipoDocumentoJpaRepository.getReferenceById(entity.getIdTipoDocumento()));
		jpa.setSexo(sexoJpaRepository.getReferenceById(entity.getIdSexo()));
		jpa.setEps(epsJpaRepository.getReferenceById(entity.getIdEps()));
		jpa.setNumeroIdentificacion(entity.getNumeroIdentificacion());
		jpa.setNombre(entity.getNombre());
		jpa.setApellido(entity.getApellido());
		jpa.setFechaNacimiento(entity.getFechaNacimiento());
		jpa.setCelular(entity.getCelular());
		jpa.setCorreo(entity.getCorreo());
		jpa.setObservacionClinica(entity.getObservacionClinica());
	}

	private PacienteEntity toDomain(PacienteJPAEntity jpa) {
		PacienteEntity e = new PacienteEntity();
		e.setId(jpa.getIdPaciente());
		e.setNumeroIdentificacion(jpa.getNumeroIdentificacion());
		e.setIdTipoDocumento(jpa.getTipoDocumento().getIdTipoDocumento());
		e.setIdSexo(jpa.getSexo().getIdSexo());
		e.setIdEps(jpa.getEps().getIdEps());
		e.setNombre(jpa.getNombre());
		e.setApellido(jpa.getApellido());
		e.setFechaNacimiento(jpa.getFechaNacimiento());
		e.setCelular(jpa.getCelular());
		e.setCorreo(jpa.getCorreo());
		e.setObservacionClinica(jpa.getObservacionClinica());
		e.setFechaCreacion(jpa.getFechaCreacion());
		e.setFechaActualizacion(jpa.getFechaActualizacion());
		return e;
	}
}
