package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.RegisterNewPatientUseCase;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.validator.ValidateRegisterNewPatient;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.query.MicrolabCatalogResolver;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterNewPatientUseCaseImpl implements RegisterNewPatientUseCase {

	private final PacienteRepository pacienteRepository;
	private final ValidateRegisterNewPatient validateRegisterNewPatient;
	private final MicrolabCatalogResolver catalogResolver;

	public RegisterNewPatientUseCaseImpl(
			PacienteRepository pacienteRepository,
			ValidateRegisterNewPatient validateRegisterNewPatient,
			MicrolabCatalogResolver catalogResolver) {
		this.pacienteRepository = pacienteRepository;
		this.validateRegisterNewPatient = validateRegisterNewPatient;
		this.catalogResolver = catalogResolver;
	}

	@Override
	public UUID execute(RegisterNewPatientDomain data) {
		validateRegisterNewPatient.validate(data);
		if (pacienteRepository.existsByNumeroIdentificacionIgnoreCase(data.getNumeroIdentificacion())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_DUPLICADA);
		}
		PacienteEntity entity = toEntity(data);
		pacienteRepository.create(entity);
		return data.getId();
	}

	private PacienteEntity toEntity(RegisterNewPatientDomain data) {
		PacienteEntity entity = new PacienteEntity();
		entity.setId(data.getId());
		entity.setNumeroIdentificacion(data.getNumeroIdentificacion());
		entity.setIdTipoDocumento(catalogResolver.resolveTipoDocumento(data.getCodigoTipoDocumento()));
		entity.setIdSexo(catalogResolver.resolveSexo(data.getSexo()));
		entity.setIdEps(catalogResolver.resolveEps(data.getCodigoEps()));
		entity.setNombre(data.getNombre());
		entity.setApellido(data.getApellido());
		entity.setFechaNacimiento(data.getFechaNacimiento());
		entity.setCelular(data.getCelular());
		entity.setCorreo(data.getCorreo());
		entity.setObservacionClinica(data.getObservacionClinica());
		return entity;
	}
}
