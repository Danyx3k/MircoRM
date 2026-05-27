package co.hospital.MicroRM.features.paciente.updatepatient.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;

import java.util.UUID;

public interface UpdatePacienteUseCase {

	PacienteEntity execute(UUID id, RegisterPacienteRequest request);
}
