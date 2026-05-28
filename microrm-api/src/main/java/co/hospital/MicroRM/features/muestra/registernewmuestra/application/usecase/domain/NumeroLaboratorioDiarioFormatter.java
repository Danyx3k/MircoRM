package co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain;

import java.time.LocalDate;

/**
 * Formato YYMMDD + tres dígitos (000-999), único por día entre todos los pacientes.
 */
public final class NumeroLaboratorioDiarioFormatter {

	private NumeroLaboratorioDiarioFormatter() {
	}

	public static String format(LocalDate fechaNegocio, int indiceDelDia) {
		if (indiceDelDia < 0 || indiceDelDia > 999) {
			throw new IllegalArgumentException("indiceDelDia debe estar entre 0 y 999");
		}
		int yy = fechaNegocio.getYear() % 100;
		return String.format("%02d%02d%02d%03d", yy, fechaNegocio.getMonthValue(), fechaNegocio.getDayOfMonth(), indiceDelDia);
	}
}
