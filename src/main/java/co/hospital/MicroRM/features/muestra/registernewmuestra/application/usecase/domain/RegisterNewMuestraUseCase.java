package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain;

import co.hospital.MicroRM.application.usecase.UseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;

import java.util.UUID;

public interface RegisterNewMuestraUseCase extends UseCase<MuestraEntity, UUID> {
}
