package co.hospital.MicroRM.features.paciente.getpaciente.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;

import java.util.UUID;

public interface GetPacienteUseCase {

	PacienteEntity execute(UUID id);
}
