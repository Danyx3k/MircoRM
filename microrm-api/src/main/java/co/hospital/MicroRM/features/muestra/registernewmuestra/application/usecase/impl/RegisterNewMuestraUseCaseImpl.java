package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.RegisterNewMuestraUseCase;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator.ValidateRegisterNewMuestra;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.query.ColaboradorContextService;
import co.hospital.MicroRM.infrastructure.persistence.query.MicrolabCatalogResolver;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SitioAnatomicoJpaRepository;
import co.hospital.MicroRM.infrastructure.messaging.domain.MuestraRegistradaEvent;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import org.springframework.context.ApplicationEventPublisher;
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
	private final ColaboradorContextService colaboradorContextService;
	private final ValidateRegisterNewMuestra validateRegisterNewMuestra;
	private final ApplicationEventPublisher applicationEventPublisher;

	public RegisterNewMuestraUseCaseImpl(
			MuestraRepository muestraRepository,
			PacienteRepository pacienteRepository,
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			MicrolabCatalogResolver catalogResolver,
			ColaboradorContextService colaboradorContextService,
			ValidateRegisterNewMuestra validateRegisterNewMuestra,
			ApplicationEventPublisher applicationEventPublisher) {
		this.muestraRepository = muestraRepository;
		this.pacienteRepository = pacienteRepository;
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.catalogResolver = catalogResolver;
		this.colaboradorContextService = colaboradorContextService;
		this.validateRegisterNewMuestra = validateRegisterNewMuestra;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	@Transactional
	public UUID execute(MuestraEntity data) {
		data.setIdEstado(catalogResolver.estadoRecibidaId());
		data.setIdColaborador(colaboradorContextService.resolveColaboradorCapturaId());
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
		UUID idMuestra = muestraRepository.create(data);
		applicationEventPublisher.publishEvent(
				new MuestraRegistradaEvent(idMuestra, data.getIdPaciente(), data.getNumeroLaboratorio()));
		return idMuestra;
	}
}
