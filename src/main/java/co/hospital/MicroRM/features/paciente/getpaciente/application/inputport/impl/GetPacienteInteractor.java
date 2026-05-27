package co.hospital.MicroRM.features.paciente.getpaciente.application.inputport.impl;

import co.hospital.MicroRM.features.paciente.getpaciente.application.inputport.GetPacienteInputPort;
import co.hospital.MicroRM.features.paciente.getpaciente.application.usecase.GetPacienteUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPacienteInteractor implements GetPacienteInputPort {

	private final GetPacienteUseCase useCase;

	public GetPacienteInteractor(GetPacienteUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public PacienteEntity execute(UUID id) {
		return useCase.execute(id);
	}
}
