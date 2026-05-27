package co.hospital.MicroRM.features.paciente.registernewpatient.application.inputport.impl;

import co.hospital.MicroRM.features.paciente.registernewpatient.application.inputport.RegisterNewPatientInputPort;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.RegisterNewPatientUseCase;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterNewPatientInteractor implements RegisterNewPatientInputPort {

	private final RegisterNewPatientUseCase useCase;

	public RegisterNewPatientInteractor(RegisterNewPatientUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public UUID execute(RegisterPacienteRequest data) {
		RegisterNewPatientDomain domain = RegisterNewPatientDomain.from(data);
		return useCase.execute(domain);
	}
}
