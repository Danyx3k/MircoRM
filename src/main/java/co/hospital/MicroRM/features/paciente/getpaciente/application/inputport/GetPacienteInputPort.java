package co.hospital.MicroRM.features.paciente.getpaciente.application.inputport;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;

import java.util.UUID;

public interface GetPacienteInputPort {

	PacienteEntity execute(UUID id);
}
