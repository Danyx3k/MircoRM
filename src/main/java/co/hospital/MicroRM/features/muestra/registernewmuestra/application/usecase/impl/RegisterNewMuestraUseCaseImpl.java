package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.RegisterNewMuestraUseCase;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator.ValidateRegisterNewMuestra;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.NumeroLaboratorioDiarioFormatter;
import co.hospital.MicroRM.infrastructure.persistence.repository.ColaboradorRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.MedioCultivoRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.NumeroLaboratorioSequencePort;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.TipoMuestraRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegisterNewMuestraUseCaseImpl implements RegisterNewMuestraUseCase {

	private final MuestraRepository muestraRepository;
	private final PacienteRepository pacienteRepository;
	private final TipoMuestraRepository tipoMuestraRepository;
	private final MedioCultivoRepository medioCultivoRepository;
	private final ColaboradorRepository colaboradorRepository;
	private final NumeroLaboratorioSequencePort numeroLaboratorioSequencePort;
	private final Clock microRmClock;
	private final ValidateRegisterNewMuestra validateRegisterNewMuestra;

	public RegisterNewMuestraUseCaseImpl(
			MuestraRepository muestraRepository,
			PacienteRepository pacienteRepository,
			TipoMuestraRepository tipoMuestraRepository,
			MedioCultivoRepository medioCultivoRepository,
			ColaboradorRepository colaboradorRepository,
			NumeroLaboratorioSequencePort numeroLaboratorioSequencePort,
			Clock microRmClock,
			ValidateRegisterNewMuestra validateRegisterNewMuestra) {
		this.muestraRepository = muestraRepository;
		this.pacienteRepository = pacienteRepository;
		this.tipoMuestraRepository = tipoMuestraRepository;
		this.medioCultivoRepository = medioCultivoRepository;
		this.colaboradorRepository = colaboradorRepository;
		this.numeroLaboratorioSequencePort = numeroLaboratorioSequencePort;
		this.microRmClock = microRmClock;
		this.validateRegisterNewMuestra = validateRegisterNewMuestra;
	}

	@Override
	@Transactional
	public UUID execute(MuestraEntity data) {
		validateRegisterNewMuestra.validateSinNumeroLaboratorio(data);
		if (!pacienteRepository.existsById(data.getIdPaciente())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_PACIENTE_NO_EXISTE);
		}
		if (!tipoMuestraRepository.existsById(data.getIdTipoMuestra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_TIPO_NO_EXISTE);
		}
		if (!medioCultivoRepository.existsById(data.getIdMedioCultivo())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_MEDIO_NO_EXISTE);
		}
		if (!colaboradorRepository.existsActivoById(data.getIdColaboradorRegistra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_COLABORADOR_REGISTRA_NO_ACTIVO);
		}
		if (!colaboradorRepository.existsActivoById(data.getIdColaboradorProcesa())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_COLABORADOR_PROCESA_NO_ACTIVO);
		}
		LocalDate fechaNegocio = LocalDate.now(microRmClock);
		int indice = numeroLaboratorioSequencePort.reserveNextIndex(fechaNegocio);
		data.setNumeroLaboratorio(NumeroLaboratorioDiarioFormatter.format(fechaNegocio, indice));
		validateRegisterNewMuestra.validateNumeroLaboratorioAsignado(data);
		if (data.getId() == null) {
			data.setId(UUID.randomUUID());
		}
		muestraRepository.create(data);
		return data.getId();
	}
}
