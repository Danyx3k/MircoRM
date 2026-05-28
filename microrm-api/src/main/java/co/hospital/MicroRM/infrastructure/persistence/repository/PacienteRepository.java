package co.hospital.MicroRM.infrastructure.persistence.repository;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;

import java.util.Optional;
import java.util.UUID;

public interface PacienteRepository {

	void create(PacienteEntity entity);

	void update(PacienteEntity entity);

	Optional<PacienteEntity> findById(UUID id);

	boolean existsByNumeroIdentificacionIgnoreCase(String numeroIdentificacion);

	boolean existsByNumeroIdentificacionIgnoreCaseExcludingId(String numeroIdentificacion, UUID excludeId);

	boolean existsById(UUID id);
}
