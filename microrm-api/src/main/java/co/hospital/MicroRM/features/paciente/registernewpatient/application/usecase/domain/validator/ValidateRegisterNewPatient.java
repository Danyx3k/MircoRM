package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.validator;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.crossscutting.specification.generics.StringLengthSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.StringValuePresentSpecification;
import co.hospital.MicroRM.crossscutting.validator.Validator;
import co.hospital.MicroRM.crossscutting.helper.PacienteEdadCalculator;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class ValidateRegisterNewPatient implements Validator {

	private final StringValuePresentSpecification stringPresent = new StringValuePresentSpecification();
	private final StringLengthSpecification nombreLength = new StringLengthSpecification(1, 100, true);
	private final Clock microRmClock;

	public ValidateRegisterNewPatient(Clock microRmClock) {
		this.microRmClock = microRmClock;
	}

	@Override
	public void validate(Object... data) {
		if (ObjectHelper.isNull(data) || data.length < 1 || !(data[0] instanceof RegisterNewPatientDomain candidate)) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		validate(candidate);
	}

	public void validate(RegisterNewPatientDomain candidate) {
		if (!stringPresent.isSatisfiedBy(candidate.getNumeroIdentificacion())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_REQUERIDA);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getNombre())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_NOMBRE_REQUERIDO);
		}
		if (!nombreLength.isSatisfiedBy(candidate.getNombre())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_NOMBRE_LONGITUD);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getApellido())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_APELLIDO_REQUERIDO);
		}
		if (!nombreLength.isSatisfiedBy(candidate.getApellido())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_APELLIDO_LONGITUD);
		}
		if (!PacienteEdadCalculator.esFechaNacimientoValida(candidate.getFechaNacimiento(), microRmClock)) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_FECHA_NACIMIENTO_INVALIDA);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getCodigoTipoDocumento())
				|| !stringPresent.isSatisfiedBy(candidate.getSexo())
				|| !stringPresent.isSatisfiedBy(candidate.getCodigoEps())) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
	}
}
