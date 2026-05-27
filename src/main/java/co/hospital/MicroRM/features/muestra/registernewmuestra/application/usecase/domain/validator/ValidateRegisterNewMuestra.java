package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.validator;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.crossscutting.specification.generics.AllowedStringValuesSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.IntNonNegativeSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.StringValuePresentSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.UuidValuePresentSpecification;
import co.hospital.MicroRM.crossscutting.validator.Validator;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ValidateRegisterNewMuestra implements Validator {

	private final StringValuePresentSpecification stringPresent = new StringValuePresentSpecification();
	private final UuidValuePresentSpecification uuidPresent = new UuidValuePresentSpecification();
	private final IntNonNegativeSpecification intNonNeg = new IntNonNegativeSpecification();
	private final AllowedStringValuesSpecification estadoSpec =
			new AllowedStringValuesSpecification(
					"RECIBIDA", "EN_PROCESO", "PROCESADA", "CONTAMINADA", "FINALIZADA", "RECHAZADA");

	@Override
	public void validate(Object... data) {
		if (ObjectHelper.isNull(data) || data.length < 1 || !(data[0] instanceof MuestraEntity candidate)) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		validate(candidate);
	}

	public void validate(MuestraEntity candidate) {
		validateSinNumeroLaboratorio(candidate);
		validateNumeroLaboratorioAsignado(candidate);
	}

	/** Validación del cuerpo de la solicitud antes de asignar el consecutivo diario. */
	public void validateSinNumeroLaboratorio(MuestraEntity candidate) {
		if (!uuidPresent.isSatisfiedBy(candidate.getIdPaciente())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_PACIENTE_REQUERIDO);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdTipoMuestra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_TIPO_REQUERIDO);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdMedioCultivo())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_MEDIO_REQUERIDO);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getOrigenAnatomico())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ORIGEN_REQUERIDO);
		}
		if (ObjectHelper.isNull(candidate.getFechaHoraToma())) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		if (!estadoSpec.isSatisfiedBy(candidate.getEstado())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ESTADO_INVALIDO);
		}
		if (!intNonNeg.isSatisfiedBy(candidate.getCantidadMorfotiposBacterianos())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_MORFOTIPOS_INVALIDOS);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdColaboradorRegistra())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_COLABORADOR_REGISTRA_REQUERIDO);
		}
		if (!uuidPresent.isSatisfiedBy(candidate.getIdColaboradorProcesa())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_COLABORADOR_PROCESA_REQUERIDO);
		}
		if (ObjectHelper.isNull(candidate.getEsContaminada())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_ES_CONTAMINADA_REQUERIDA);
		}
		Instant recepcion = candidate.getFechaHoraRecepcion();
		if (recepcion != null && recepcion.isBefore(candidate.getFechaHoraToma())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_FECHAS_INVALIDAS);
		}
		Instant procesamiento = candidate.getFechaHoraProcesamiento();
		if (procesamiento != null) {
			if (procesamiento.isBefore(candidate.getFechaHoraToma())) {
				throw MicroRMException.of(MessagesEnum.MUESTRA_FECHAS_INVALIDAS);
			}
			if (recepcion != null && procesamiento.isBefore(recepcion)) {
				throw MicroRMException.of(MessagesEnum.MUESTRA_FECHAS_INVALIDAS);
			}
		}
	}

	public void validateNumeroLaboratorioAsignado(MuestraEntity candidate) {
		if (!stringPresent.isSatisfiedBy(candidate.getNumeroLaboratorio())) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_NUMERO_LAB_REQUERIDO);
		}
	}
}
