package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pacientes")
public class PacienteJPAEntity {

	@Id
	@Column(name = "id_paciente", nullable = false)
	private UUID idPaciente;

	@Column(name = "identificacion", nullable = false, unique = true, length = 15)
	private String identificacion;

	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 20)
	private String apellido;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "genero", nullable = false, length = 20)
	private String genero;

	@Column(name = "telefono", length = 30)
	private String telefono;

	@Column(name = "email", length = 160)
	private String email;

	@Column(name = "eps_seguro", nullable = false, length = 120)
	private String epsSeguro;

	@Column(name = "observaciones_clinicas", columnDefinition = "text")
	private String observacionesClinicas;

	@Column(name = "fecha_creacion", nullable = false)
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private Instant fechaActualizacion;

	@PrePersist
	void onCreate() {
		Instant now = Instant.now();
		if (fechaCreacion == null) {
			fechaCreacion = now;
		}
		fechaActualizacion = now;
	}

	@PreUpdate
	void onUpdate() {
		fechaActualizacion = Instant.now();
	}

	public UUID getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(UUID idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEpsSeguro() {
		return epsSeguro;
	}

	public void setEpsSeguro(String epsSeguro) {
		this.epsSeguro = epsSeguro;
	}

	public String getObservacionesClinicas() {
		return observacionesClinicas;
	}

	public void setObservacionesClinicas(String observacionesClinicas) {
		this.observacionesClinicas = observacionesClinicas;
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
