package co.hospital.MicroRM.infrastructure.persistence.repository;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;

import java.util.UUID;

public interface PacienteRepository {

	void create(PacienteEntity entity);

	boolean existsByIdentificacionIgnoreCase(String identificacion);

	boolean existsById(UUID id);
}
