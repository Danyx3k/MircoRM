package co.hospital.MicroRM.features.muestra.getmuestra.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;

import java.util.UUID;

public interface GetMuestraUseCase {

	MuestraEntity execute(UUID id);
}
