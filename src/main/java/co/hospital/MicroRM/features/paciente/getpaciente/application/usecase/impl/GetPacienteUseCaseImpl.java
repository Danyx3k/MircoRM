package co.hospital.MicroRM.features.paciente.getpaciente.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.paciente.getpaciente.application.usecase.GetPacienteUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetPacienteUseCaseImpl implements GetPacienteUseCase {

	private final PacienteRepository pacienteRepository;

	public GetPacienteUseCaseImpl(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public PacienteEntity execute(UUID id) {
		return pacienteRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
	}
}
