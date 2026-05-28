package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sexo")
public class SexoJPAEntity {

	@Id
	@Column(name = "id_sexo")
	private UUID idSexo;

	@Column(nullable = false, unique = true, length = 100)
	private String nombre;

	@Column(name = "fecha_creacion")
	private Instant fechaCreacion;

	public UUID getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(UUID idSexo) {
		this.idSexo = idSexo;
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
