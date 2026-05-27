package co.hospital.MicroRM.features.muestra.updatemuestra.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;

import java.util.UUID;

public interface UpdateMuestraUseCase {

	MuestraEntity execute(UUID id, RegisterMuestraRequest request);
}
