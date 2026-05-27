package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

/**
 * Dominio del caso de uso registrar paciente (equivalente a {@code RegisterNewStudentDomain} en UcoParking).
 */
public final class RegisterNewPatientDomain {

	private final UUID id;
	private final String identificacion;
	private final String nombre;
	private final String apellido;
	private final LocalDate fechaNacimiento;
	private final String genero;
	private final String telefono;
	private final String email;
	private final String epsSeguro;
	private final String observacionesClinicas;

	private RegisterNewPatientDomain(
			UUID id,
			String identificacion,
			String nombre,
			String apellido,
			LocalDate fechaNacimiento,
			String genero,
			String telefono,
			String email,
			String epsSeguro,
			String observacionesClinicas) {
		this.id = id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.telefono = telefono;
		this.email = email;
		this.epsSeguro = epsSeguro;
		this.observacionesClinicas = observacionesClinicas;
	}

	public static RegisterNewPatientDomain from(RegisterPacienteRequest request) {
		return new RegisterNewPatientDomain(
				UUID.randomUUID(),
				trim(request.identificacion()),
				trim(request.nombre()),
				trim(request.apellido()),
				request.fechaNacimiento(),
				normalizeGenero(request.genero()),
				emptyToNull(trimNullable(request.telefono())),
				trim(request.email()),
				trim(request.epsSeguro()),
				emptyToNull(trimNullable(request.observacionesClinicas())));
	}

	private static String normalizeGenero(String genero) {
		if (genero == null) {
			return null;
		}
		return genero.trim().toUpperCase(Locale.ROOT);
	}

	private static String trim(String value) {
		return value == null ? null : value.trim();
	}

	private static String trimNullable(String value) {
		return value == null ? null : value.trim();
	}

	private static String emptyToNull(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return value;
	}

	public UUID getId() {
		return id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getEpsSeguro() {
		return epsSeguro;
	}

	public String getObservacionesClinicas() {
		return observacionesClinicas;
	}
}
