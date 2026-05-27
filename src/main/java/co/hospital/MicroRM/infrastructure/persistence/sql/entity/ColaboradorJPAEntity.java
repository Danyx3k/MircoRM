package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Alineado con el DDL real de {@code microlab.colaborador} (nombre + apellido, auditoría, etc.).
 */
@Entity
@Table(name = "colaborador")
public class ColaboradorJPAEntity {

	@Id
	@Column(name = "id_colaborador")
	private UUID idColaborador;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_rol", nullable = false)
	private RolColaboradorJPAEntity rol;

	@Column(nullable = false, unique = true, length = 120)
	private String username;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellido;

	@Column(nullable = false, unique = true, length = 160)
	private String email;

	@Column(length = 30)
	private String telefono;

	@Column(nullable = false, unique = true, length = 40)
	private String cedula;

	@Column(name = "numero_licencia", length = 80)
	private String numeroLicencia;

	@Column(length = 120)
	private String especialidad;

	@Column(length = 120)
	private String departamento;

	@Column(name = "area_laboratorio", length = 120)
	private String areaLaboratorio;

	@Column(nullable = false)
	private Boolean activo = true;

	@Column(name = "fecha_ultimo_acceso")
	private Instant fechaUltimoAcceso;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion")
	private Instant fechaActualizacion;

	@Column(name = "usuario_crea", length = 80)
	private String usuarioCrea;

	@Column(name = "usuario_actualiza", length = 80)
	private String usuarioActualiza;

	public UUID getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(UUID idColaborador) {
		this.idColaborador = idColaborador;
	}

	public RolColaboradorJPAEntity getRol() {
		return rol;
	}

	public void setRol(RolColaboradorJPAEntity rol) {
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getAreaLaboratorio() {
		return areaLaboratorio;
	}

	public void setAreaLaboratorio(String areaLaboratorio) {
		this.areaLaboratorio = areaLaboratorio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Instant getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Instant fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
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

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
}
