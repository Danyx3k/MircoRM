package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cargo")
public class CargoJPAEntity {

	@Id
	@Column(name = "id_cargo")
	private UUID idCargo;

	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Column(nullable = false)
	private Boolean activo = true;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion")
	private Instant fechaActualizacion;

	public UUID getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(UUID idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
}
