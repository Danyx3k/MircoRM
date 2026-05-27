package co.hospital.MicroRM.features.muestra.updatemuestra.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator.ValidateRegisterNewMuestra;
import co.hospital.MicroRM.features.muestra.updatemuestra.application.usecase.UpdateMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SitioAnatomicoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateMuestraUseCaseImpl implements UpdateMuestraUseCase {

	private final MuestraRepository muestraRepository;
	private final PacienteRepository pacienteRepository;
	private final SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository;
	private final TipoMuestraJpaRepository tipoMuestraJpaRepository;
	private final ValidateRegisterNewMuestra validateRegisterNewMuestra;

	public UpdateMuestraUseCaseImpl(
			MuestraRepository muestraRepository,
			PacienteRepository pacienteRepository,
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			ValidateRegisterNewMuestra validateRegisterNewMuestra) {
		this.muestraRepository = muestraRepository;
		this.pacienteRepository = pacienteRepository;
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.validateRegisterNewMuestra = validateRegisterNewMuestra;
	}

	@Override
	@Transactional
	public MuestraEntity execute(UUID id, RegisterMuestraRequest request) {
		MuestraEntity existing = muestraRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_NO_ENCONTRADA));
		MuestraEntity patch = new MuestraEntity();
		patch.setId(id);
		patch.setIdPaciente(request.idPaciente());
		patch.setIdTipoMuestra(request.idTipoMuestra());
		patch.setIdSitioAnatomico(request.idSitioAnatomico());
		patch.setFechaHoraToma(request.fechaHoraToma());
		validateRegisterNewMuestra.validate(patch);
		if (!pacienteRepository.existsById(patch.getIdPaciente())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_PACIENTE_NO_EXISTE);
		}
		if (!tipoMuestraJpaRepository.existsById(patch.getIdTipoMuestra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_TIPO_NO_EXISTE);
		}
		if (!sitioAnatomicoJpaRepository.existsById(patch.getIdSitioAnatomico())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ORIGEN_REQUERIDO);
		}
		existing.setIdTipoMuestra(patch.getIdTipoMuestra());
		existing.setIdSitioAnatomico(patch.getIdSitioAnatomico());
		existing.setFechaHoraToma(patch.getFechaHoraToma());
		muestraRepository.updateCaptura(existing);
		return muestraRepository.findById(id).orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_NO_ENCONTRADA));
	}
}
