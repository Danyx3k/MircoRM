package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.RegisterNewPatientUseCase;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.validator.ValidateRegisterNewPatient;
import co.hospital.MicroRM.infrastructure.persistence.mapper.RegisterNewPatientDomainToPacienteEntityMapper;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterNewPatientUseCaseImpl implements RegisterNewPatientUseCase {

	private final PacienteRepository pacienteRepository;
	private final ValidateRegisterNewPatient validateRegisterNewPatient;
	private final RegisterNewPatientDomainToPacienteEntityMapper domainToEntityMapper;

	public RegisterNewPatientUseCaseImpl(
			PacienteRepository pacienteRepository,
			ValidateRegisterNewPatient validateRegisterNewPatient,
			RegisterNewPatientDomainToPacienteEntityMapper domainToEntityMapper) {
		this.pacienteRepository = pacienteRepository;
		this.validateRegisterNewPatient = validateRegisterNewPatient;
		this.domainToEntityMapper = domainToEntityMapper;
	}

	@Override
	public UUID execute(RegisterNewPatientDomain data) {
		validateRegisterNewPatient.validate(data);
		if (pacienteRepository.existsByIdentificacionIgnoreCase(data.getIdentificacion())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_DUPLICADA);
		}
		var entity = domainToEntityMapper.toEntity(data);
		entity.setId(data.getId());
		pacienteRepository.create(entity);
		return data.getId();
	}
}
