package co.hospital.MicroRM.features.paciente.registernewpatient.application.inputport;

import co.hospital.MicroRM.application.inputport.InputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;

import java.util.UUID;

public interface RegisterNewPatientInputPort extends InputPort<RegisterPacienteRequest, UUID> {
}
