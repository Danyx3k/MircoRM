package co.hospital.MicroRM.crossscutting.helper;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;

public final class PacienteEdadCalculator {

	private static final int EDAD_MAXIMA = 150;

	private PacienteEdadCalculator() {
	}

	public static int edadEnAnios(LocalDate fechaNacimiento, Clock clock) {
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException("fechaNacimiento is required");
		}
		LocalDate hoy = LocalDate.now(clock);
		if (fechaNacimiento.isAfter(hoy)) {
			return -1;
		}
		return Period.between(fechaNacimiento, hoy).getYears();
	}

	public static boolean esFechaNacimientoValida(LocalDate fechaNacimiento, Clock clock) {
		if (fechaNacimiento == null) {
			return false;
		}
		LocalDate hoy = LocalDate.now(clock);
		if (fechaNacimiento.isAfter(hoy)) {
			return false;
		}
		return !fechaNacimiento.isBefore(hoy.minusYears(EDAD_MAXIMA));
	}
}
