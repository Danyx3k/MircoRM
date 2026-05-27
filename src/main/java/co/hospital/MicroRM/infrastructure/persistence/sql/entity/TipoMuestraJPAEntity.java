package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tipos_muestra")
public class TipoMuestraJPAEntity {

	@Id
	@Column(name = "id_tipo_muestra")
	private UUID idTipoMuestra;

	@Column(nullable = false, unique = true, length = 120)
	private String nombre;

	@Column(nullable = false, unique = true, length = 40)
	private String codigo;

	@Column(name = "tiempo_incubacion_horas", nullable = false)
	private Integer tiempoIncubacionHoras;

	@Column(columnDefinition = "text")
	private String descripcion;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getTiempoIncubacionHoras() {
		return tiempoIncubacionHoras;
	}

	public void setTiempoIncubacionHoras(Integer tiempoIncubacionHoras) {
		this.tiempoIncubacionHoras = tiempoIncubacionHoras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
