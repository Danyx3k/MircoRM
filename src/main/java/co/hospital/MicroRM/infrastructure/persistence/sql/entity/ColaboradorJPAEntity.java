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

@Entity
@Table(name = "colaborador")
public class ColaboradorJPAEntity {

	@Id
	@Column(name = "id_colaborador")
	private UUID idColaborador;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cargo", nullable = false)
	private CargoJPAEntity cargo;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellido;

	@Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
	private String correoElectronico;

	@Column(nullable = false)
	private Boolean activo = true;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion")
	private Instant fechaActualizacion;

	@Column(name = "usuario_crea", length = 100)
	private String usuarioCrea;

	@Column(name = "usuario_actualiza", length = 100)
	private String usuarioActualiza;

	public UUID getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(UUID idColaborador) {
		this.idColaborador = idColaborador;
	}

	public CargoJPAEntity getCargo() {
		return cargo;
	}

	public void setCargo(CargoJPAEntity cargo) {
		this.cargo = cargo;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
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
