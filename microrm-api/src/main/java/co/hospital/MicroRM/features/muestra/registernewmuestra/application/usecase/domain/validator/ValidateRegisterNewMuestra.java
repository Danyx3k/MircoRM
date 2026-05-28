package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.crossscutting.specification.generics.UuidValuePresentSpecification;
import co.hospital.MicroRM.crossscutting.validator.Validator;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidateRegisterNewMuestra implements Validator {

	private final UuidValuePresentSpecification uuidPresent = new UuidValuePresentSpecification();

	@Override
	public void validate(Object... data) {
		if (ObjectHelper.isNull(data) || data.length < 1 || !(data[0] instanceof MuestraEntity candidate)) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		validate(candidate);
	}

	public void validate(MuestraEntity candidate) {
		if (!uuidPresent.isSatisfiedBy(candidate.getIdPaciente())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_PACIENTE_REQUERIDO);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdTipoMuestra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_TIPO_REQUERIDO);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdSitioAnatomico())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ORIGEN_REQUERIDO);
		}
		if (ObjectHelper.isNull(candidate.getFechaHoraToma())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_FECHAS_INVALIDAS);
		}
	}
}
