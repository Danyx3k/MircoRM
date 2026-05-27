package co.hospital.MicroRM.crossscutting.messagescatalog;

public enum MessagesEnum {

	COMMON_VALIDATION_ERROR("MRM-0001", "Error de validación."),
	PACIENTE_IDENTIFICACION_REQUERIDA("MRM-1001", "La identificación del paciente es obligatoria."),
	PACIENTE_NOMBRE_REQUERIDO("MRM-1002", "El nombre es obligatorio."),
	PACIENTE_FECHA_NACIMIENTO_INVALIDA("MRM-1003", "La fecha de nacimiento no es válida."),
	PACIENTE_GENERO_INVALIDO("MRM-1004", "El género debe ser MASCULINO, FEMENINO u OTRO."),
	PACIENTE_EMAIL_INVALIDO("MRM-1005", "El correo no cumple el formato (dominio debe terminar en .com o .co)."),
	PACIENTE_TELEFONO_LONGITUD("MRM-1006", "El teléfono debe tener entre 7 y 20 caracteres."),
	PACIENTE_IDENTIFICACION_DUPLICADA("MRM-1007", "Ya existe un paciente con esa identificación."),
	PACIENTE_APELLIDO_REQUERIDO("MRM-1008", "El apellido es obligatorio."),
	PACIENTE_NOMBRE_LONGITUD("MRM-1009", "El nombre no puede superar 20 caracteres."),
	PACIENTE_APELLIDO_LONGITUD("MRM-1010", "El apellido no puede superar 20 caracteres."),
	PACIENTE_IDENTIFICACION_FORMATO("MRM-1011", "La identificación debe ser alfanumérica (máximo 15 caracteres)."),
	PACIENTE_EMAIL_REQUERIDO("MRM-1012", "El correo electrónico es obligatorio."),
	PACIENTE_EPS_REQUERIDO("MRM-1013", "La EPS es obligatoria."),
	PACIENTE_EPS_FORMATO("MRM-1014", "La EPS debe ser alfanumérica (se permiten espacios)."),
	PACIENTE_REGISTRADO_OK("MRM-1099", "Paciente registrado correctamente."),

	MUESTRA_PACIENTE_REQUERIDO("MRM-2001", "El paciente es obligatorio."),
	MUESTRA_TIPO_REQUERIDO("MRM-2002", "El tipo de muestra es obligatorio."),
	MUESTRA_MEDIO_REQUERIDO("MRM-2003", "El medio de cultivo es obligatorio."),
	MUESTRA_NUMERO_LAB_REQUERIDO("MRM-2004", "El número de laboratorio es obligatorio."),
	MUESTRA_ORIGEN_REQUERIDO("MRM-2005", "El origen anatómico es obligatorio."),
	MUESTRA_FECHAS_INVALIDAS("MRM-2006", "La fecha/hora de recepción no puede ser anterior a la toma."),
	MUESTRA_ESTADO_INVALIDO("MRM-2007", "El estado de la muestra no es válido."),
	MUESTRA_MORFOTIPOS_INVALIDOS("MRM-2008", "La cantidad de morfotipos no puede ser negativa."),
	MUESTRA_COLABORADOR_REGISTRA_REQUERIDO("MRM-2009", "El colaborador que registra la muestra es obligatorio."),
	MUESTRA_COLABORADOR_PROCESA_REQUERIDO("MRM-2014", "El colaborador que procesa la muestra es obligatorio."),
	MUESTRA_ES_CONTAMINADA_REQUERIDA("MRM-2015", "Debe indicarse si la muestra está contaminada."),
	MUESTRA_COLABORADOR_REGISTRA_NO_ACTIVO("MRM-2016", "El colaborador que registra no existe o está inactivo."),
	MUESTRA_COLABORADOR_PROCESA_NO_ACTIVO("MRM-2017", "El colaborador que procesa no existe o está inactivo."),
	MUESTRA_PACIENTE_NO_EXISTE("MRM-2010", "El paciente indicado no existe."),
	MUESTRA_TIPO_NO_EXISTE("MRM-2011", "El tipo de muestra no existe."),
	MUESTRA_MEDIO_NO_EXISTE("MRM-2012", "El medio de cultivo no existe."),
	MUESTRA_NUMERO_LAB_DUPLICADO("MRM-2013", "Ya existe una muestra con ese número de laboratorio."),
	MUESTRA_CONSECUTIVO_DIARIO_AGOTADO("MRM-2018", "Se alcanzó el máximo de muestras para el día (999)."),
	MUESTRA_REGISTRADA_OK("MRM-2099", "Muestra registrada correctamente.");

	private final String code;
	private final String defaultMessage;

	MessagesEnum(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public String getCode() {
		return code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}
}
