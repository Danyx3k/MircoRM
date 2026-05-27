package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.domain.validator;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.helper.ObjectHelper;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.crossscutting.specification.generics.AllowedStringValuesSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.LocalDateNotInFutureSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.RegexPatternSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.StringLengthSpecification;
import co.hospital.MicroRM.crossscutting.specification.generics.StringValuePresentSpecification;
import co.hospital.MicroRM.crossscutting.validator.Validator;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import org.springframework.stereotype.Component;

@Component
public class ValidateRegisterNewPatient implements Validator {

	private static final int TELEFONO_MIN = 7;
	private static final int TELEFONO_MAX = 20;

	private final StringValuePresentSpecification stringPresent = new StringValuePresentSpecification();
	private final StringLengthSpecification telefonoLength =
			new StringLengthSpecification(TELEFONO_MIN, TELEFONO_MAX, true);
	private final StringLengthSpecification nombreApellidoLength = new StringLengthSpecification(1, 20, true);
	private final LocalDateNotInFutureSpecification birthNotFuture = new LocalDateNotInFutureSpecification();
	private final AllowedStringValuesSpecification generoSpec =
			new AllowedStringValuesSpecification("MASCULINO", "FEMENINO", "OTRO");
	private final RegexPatternSpecification identificacionAlfanumerica =
			new RegexPatternSpecification("^[A-Za-z0-9]{1,15}$");
	private final RegexPatternSpecification emailHospitalTld =
			new RegexPatternSpecification("(?i)^[A-Za-z0-9._-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.(com|co)$");
	private final RegexPatternSpecification epsAlfanumericaEspacios = new RegexPatternSpecification("^[A-Za-z0-9 ]+$");

	@Override
	public void validate(Object... data) {
		if (ObjectHelper.isNull(data) || data.length < 1 || !(data[0] instanceof RegisterNewPatientDomain candidate)) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		validate(candidate);
	}

	public void validate(RegisterNewPatientDomain candidate) {
		if (!stringPresent.isSatisfiedBy(candidate.getIdentificacion())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_REQUERIDA);
		}
		if (!identificacionAlfanumerica.isSatisfiedBy(candidate.getIdentificacion())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_FORMATO);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getNombre())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_NOMBRE_REQUERIDO);
		}
		if (!nombreApellidoLength.isSatisfiedBy(candidate.getNombre())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_NOMBRE_LONGITUD);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getApellido())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_APELLIDO_REQUERIDO);
		}
		if (!nombreApellidoLength.isSatisfiedBy(candidate.getApellido())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_APELLIDO_LONGITUD);
		}
		if (!birthNotFuture.isSatisfiedBy(candidate.getFechaNacimiento())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_FECHA_NACIMIENTO_INVALIDA);
		}
		if (!generoSpec.isSatisfiedBy(candidate.getGenero())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_GENERO_INVALIDO);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getEmail())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_EMAIL_REQUERIDO);
		}
		if (!emailHospitalTld.isSatisfiedBy(candidate.getEmail())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_EMAIL_INVALIDO);
		}
		if (!stringPresent.isSatisfiedBy(candidate.getEpsSeguro())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_EPS_REQUERIDO);
		}
		if (!epsAlfanumericaEspacios.isSatisfiedBy(candidate.getEpsSeguro())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_EPS_FORMATO);
		}
		if (candidate.getTelefono() != null && !telefonoLength.isSatisfiedBy(candidate.getTelefono())) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_TELEFONO_LONGITUD);
		}
	}
}
