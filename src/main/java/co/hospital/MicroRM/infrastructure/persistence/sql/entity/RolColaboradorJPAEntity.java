package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "roles_colaborador")
public class RolColaboradorJPAEntity {

	@Id
	@Column(name = "id_rol")
	private UUID idRol;

	@Column(nullable = false, unique = true, length = 120)
	private String nombre;

	@Column(nullable = false, unique = true, length = 40)
	private String codigo;

	@Column(columnDefinition = "text")
	private String descripcion;

	@Column(nullable = false)
	private Boolean activo = true;

	public UUID getIdRol() {
		return idRol;
	}

	public void setIdRol(UUID idRol) {
		this.idRol = idRol;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
