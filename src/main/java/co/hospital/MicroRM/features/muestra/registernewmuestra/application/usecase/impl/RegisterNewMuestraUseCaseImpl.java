package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.RegisterNewMuestraUseCase;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator.ValidateRegisterNewMuestra;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.query.MicrolabCatalogResolver;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SitioAnatomicoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegisterNewMuestraUseCaseImpl implements RegisterNewMuestraUseCase {

	private final MuestraRepository muestraRepository;
	private final PacienteRepository pacienteRepository;
	private final SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository;
	private final TipoMuestraJpaRepository tipoMuestraJpaRepository;
	private final MicrolabCatalogResolver catalogResolver;
	private final ValidateRegisterNewMuestra validateRegisterNewMuestra;

	public RegisterNewMuestraUseCaseImpl(
			MuestraRepository muestraRepository,
			PacienteRepository pacienteRepository,
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			MicrolabCatalogResolver catalogResolver,
			ValidateRegisterNewMuestra validateRegisterNewMuestra) {
		this.muestraRepository = muestraRepository;
		this.pacienteRepository = pacienteRepository;
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.catalogResolver = catalogResolver;
		this.validateRegisterNewMuestra = validateRegisterNewMuestra;
	}

	@Override
	@Transactional
	public UUID execute(MuestraEntity data) {
		data.setIdEstado(catalogResolver.estadoRecibidaId());
		data.setIdColaborador(catalogResolver.colaboradorCapturaMuestra());
		validateRegisterNewMuestra.validate(data);
		if (!pacienteRepository.existsById(data.getIdPaciente())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_PACIENTE_NO_EXISTE);
		}
		if (!tipoMuestraJpaRepository.existsById(data.getIdTipoMuestra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_TIPO_NO_EXISTE);
		}
		if (!sitioAnatomicoJpaRepository.existsById(data.getIdSitioAnatomico())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ORIGEN_REQUERIDO);
		}
		if (data.getId() == null) {
			data.setId(UUID.randomUUID());
		}
		return muestraRepository.create(data);
	}
}
