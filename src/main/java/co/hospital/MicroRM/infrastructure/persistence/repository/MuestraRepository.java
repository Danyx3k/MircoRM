package co.hospital.MicroRM.infrastructure.persistence.repository;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;

public interface MuestraRepository {

	void create(MuestraEntity entity);

	boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio);
}
