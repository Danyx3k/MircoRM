package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Catálogo alineado con {@code microlab.medios_cultivo} (temperatura de incubación, no {@code temperatura_celsius}).
 */
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

	@Column(name = "agar_tipo", length = 120)
	private String agarTipo;

	@Column(name = "temperatura_incubacion", nullable = false)
	private Float temperaturaIncubacion;

	@Column(name = "tiempo_incubacion_horas", nullable = false)
	private Integer tiempoIncubacionHoras;

	@Column(columnDefinition = "text")
	private String composicion;

	@Column(length = 200)
	private String proveedor;

	@Column(nullable = false)
	private Boolean activo;

	@Column(name = "fecha_creacion", nullable = false)
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private Instant fechaActualizacion;

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

	public String getAgarTipo() {
		return agarTipo;
	}

	public void setAgarTipo(String agarTipo) {
		this.agarTipo = agarTipo;
	}

	public Float getTemperaturaIncubacion() {
		return temperaturaIncubacion;
	}

	public void setTemperaturaIncubacion(Float temperaturaIncubacion) {
		this.temperaturaIncubacion = temperaturaIncubacion;
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

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
