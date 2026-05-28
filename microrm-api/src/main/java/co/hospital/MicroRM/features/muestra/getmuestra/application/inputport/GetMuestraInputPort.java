package co.hospital.MicroRM.features.muestra.getmuestra.application.inputport;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;

import java.util.UUID;

public interface GetMuestraInputPort {

	MuestraEntity execute(UUID id);
}
