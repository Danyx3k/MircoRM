package co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;

import java.time.LocalDate;
import java.util.UUID;

public final class RegisterNewPatientDomain {

	private final UUID id;
	private final String numeroIdentificacion;
	private final String nombre;
	private final String apellido;
	private final LocalDate fechaNacimiento;
	private final String codigoTipoDocumento;
	private final String sexo;
	private final String codigoEps;
	private final String celular;
	private final String correo;
	private final String observacionClinica;

	private RegisterNewPatientDomain(
			UUID id,
			String numeroIdentificacion,
			String nombre,
			String apellido,
			LocalDate fechaNacimiento,
			String codigoTipoDocumento,
			String sexo,
			String codigoEps,
			String celular,
			String correo,
			String observacionClinica) {
		this.id = id;
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoTipoDocumento = codigoTipoDocumento;
		this.sexo = sexo;
		this.codigoEps = codigoEps;
		this.celular = celular;
		this.correo = correo;
		this.observacionClinica = observacionClinica;
	}

	public static RegisterNewPatientDomain from(RegisterPacienteRequest request) {
		return new RegisterNewPatientDomain(
				UUID.randomUUID(),
				trim(request.numeroIdentificacion()),
				trim(request.nombre()),
				trim(request.apellido()),
				request.fechaNacimiento(),
				trim(request.codigoTipoDocumento()),
				trim(request.sexo()),
				trim(request.codigoEps()),
				emptyToNull(trimNullable(request.celular())),
				emptyToNull(trimNullable(request.correo())),
				emptyToNull(trimNullable(request.observacionClinica())));
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

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
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

	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public String getSexo() {
		return sexo;
	}

	public String getCodigoEps() {
		return codigoEps;
	}

	public String getCelular() {
		return celular;
	}

	public String getCorreo() {
		return correo;
	}

	public String getObservacionClinica() {
		return observacionClinica;
	}
}
