package co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport;

import co.hospital.MicroRM.application.inputport.InputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;

import java.util.UUID;

public interface RegisterNewMuestraInputPort extends InputPort<RegisterMuestraRequest, UUID> {
}
