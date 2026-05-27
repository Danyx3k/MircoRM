package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

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

	@Column(name = "nombre_completo", nullable = false, length = 200)
	private String nombreCompleto;

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

	@Column(nullable = false)
	private Boolean activo = true;

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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
