package co.hospital.MicroRM.features.paciente.updatepatient.application.inputport.impl;

import co.hospital.MicroRM.features.paciente.updatepatient.application.inputport.UpdatePacienteInputPort;
import co.hospital.MicroRM.features.paciente.updatepatient.application.usecase.UpdatePacienteUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePacienteInteractor implements UpdatePacienteInputPort {

	private final UpdatePacienteUseCase useCase;

	public UpdatePacienteInteractor(UpdatePacienteUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public PacienteEntity execute(UUID id, RegisterPacienteRequest request) {
		return useCase.execute(id, request);
	}
}
