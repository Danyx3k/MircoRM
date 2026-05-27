package co.hospital.MicroRM.features.paciente.updatepatient.application.inputport;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;

import java.util.UUID;

public interface UpdatePacienteInputPort {

	PacienteEntity execute(UUID id, RegisterPacienteRequest request);
}
