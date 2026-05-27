package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "medios_cultivo")
public class MedioCultivoJPAEntity {

	@Id
	@Column(name = "id_medio_cultivo")
	private UUID idMedioCultivo;

	@Column(nullable = false, unique = true, length = 120)
	private String nombre;

	@Column(nullable = false, unique = true, length = 40)
	private String codigo;

	@Column(name = "temperatura_celsius", nullable = false)
	private Float temperaturaCelsius;

	@Column(name = "tiempo_incubacion_horas", nullable = false)
	private Integer tiempoIncubacionHoras;

	@Column(columnDefinition = "text")
	private String composicion;

	@Column(name = "agar_tipo", length = 120)
	private String agarTipo;

	public UUID getIdMedioCultivo() {
		return idMedioCultivo;
	}

	public void setIdMedioCultivo(UUID idMedioCultivo) {
		this.idMedioCultivo = idMedioCultivo;
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

	public Float getTemperaturaCelsius() {
		return temperaturaCelsius;
	}

	public void setTemperaturaCelsius(Float temperaturaCelsius) {
		this.temperaturaCelsius = temperaturaCelsius;
	}

	public Integer getTiempoIncubacionHoras() {
		return tiempoIncubacionHoras;
	}

	public void setTiempoIncubacionHoras(Integer tiempoIncubacionHoras) {
		this.tiempoIncubacionHoras = tiempoIncubacionHoras;
	}

	public String getComposicion() {
		return composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	public String getAgarTipo() {
		return agarTipo;
	}

	public void setAgarTipo(String agarTipo) {
		this.agarTipo = agarTipo;
	}
}
