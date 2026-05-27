package co.hospital.MicroRM.features.paciente.updatepatient.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.validator.ValidateRegisterNewPatient;
import co.hospital.MicroRM.features.paciente.updatepatient.application.usecase.UpdatePacienteUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;
import co.hospital.MicroRM.infrastructure.persistence.query.MicrolabCatalogResolver;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdatePacienteUseCaseImpl implements UpdatePacienteUseCase {

	private final PacienteRepository pacienteRepository;
	private final ValidateRegisterNewPatient validateRegisterNewPatient;
	private final MicrolabCatalogResolver catalogResolver;

	public UpdatePacienteUseCaseImpl(
			PacienteRepository pacienteRepository,
			ValidateRegisterNewPatient validateRegisterNewPatient,
			MicrolabCatalogResolver catalogResolver) {
		this.pacienteRepository = pacienteRepository;
		this.validateRegisterNewPatient = validateRegisterNewPatient;
		this.catalogResolver = catalogResolver;
	}

	@Override
	@Transactional
	public PacienteEntity execute(UUID id, RegisterPacienteRequest request) {
		RegisterNewPatientDomain datos = RegisterNewPatientDomain.from(request);
		validateRegisterNewPatient.validate(datos);
		if (!pacienteRepository.existsById(id)) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO);
		}
		if (pacienteRepository.existsByNumeroIdentificacionIgnoreCaseExcludingId(datos.getNumeroIdentificacion(), id)) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_DUPLICADA);
		}
		PacienteEntity existing = pacienteRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
		existing.setNumeroIdentificacion(datos.getNumeroIdentificacion());
		existing.setIdTipoDocumento(catalogResolver.resolveTipoDocumento(datos.getCodigoTipoDocumento()));
		existing.setIdSexo(catalogResolver.resolveSexo(datos.getSexo()));
		existing.setIdEps(catalogResolver.resolveEps(datos.getCodigoEps()));
		existing.setNombre(datos.getNombre());
		existing.setApellido(datos.getApellido());
		existing.setFechaNacimiento(datos.getFechaNacimiento());
		existing.setCelular(datos.getCelular());
		existing.setCorreo(datos.getCorreo());
		existing.setObservacionClinica(datos.getObservacionClinica());
		pacienteRepository.update(existing);
		return pacienteRepository.findById(id).orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
	}
}
