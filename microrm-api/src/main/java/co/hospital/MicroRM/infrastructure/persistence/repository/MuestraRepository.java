package co.hospital.MicroRM.infrastructure.persistence.repository;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;

import java.util.Optional;
import java.util.UUID;

public interface MuestraRepository {

	UUID create(MuestraEntity entity);

	void updateCaptura(MuestraEntity entity);

	Optional<MuestraEntity> findById(UUID id);

	boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio);

	boolean existsByNumeroLaboratorioIgnoreCaseExcludingId(String numeroLaboratorio, UUID excludeId);
}
