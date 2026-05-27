package co.hospital.MicroRM.infrastructure.persistence.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class PacienteEntity {

	private UUID id;
	private String numeroIdentificacion;
	private UUID idTipoDocumento;
	private UUID idSexo;
	private UUID idEps;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String celular;
	private String correo;
	private String observacionClinica;
	private Instant fechaCreacion;
	private Instant fechaActualizacion;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public UUID getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(UUID idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public UUID getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(UUID idSexo) {
		this.idSexo = idSexo;
	}

	public UUID getIdEps() {
		return idEps;
	}

	public void setIdEps(UUID idEps) {
		this.idEps = idEps;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getObservacionClinica() {
		return observacionClinica;
	}

	public void setObservacionClinica(String observacionClinica) {
		this.observacionClinica = observacionClinica;
	}

	public Instant getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Instant fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Instant getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Instant fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
}
