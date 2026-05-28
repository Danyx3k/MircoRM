package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "eps")
public class EpsJPAEntity {

	@Id
	@Column(name = "id_eps")
	private UUID idEps;

	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Column(nullable = false, unique = true, length = 10)
	private String codigo;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Instant getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Instant fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
