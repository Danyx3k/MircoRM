package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "estado")
public class EstadoJPAEntity {

	@Id
	@Column(name = "id_estado")
	private UUID idEstado;

	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	public UUID getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(UUID idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Instant getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Instant fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
