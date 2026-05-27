package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tipo_muestra")
public class TipoMuestraJPAEntity {

	@Id
	@Column(name = "id_tipo_muestra")
	private UUID idTipoMuestra;

	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	public UUID getIdTipoMuestra() {
		return idTipoMuestra;
	}

	public void setIdTipoMuestra(UUID idTipoMuestra) {
		this.idTipoMuestra = idTipoMuestra;
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
