package co.hospital.MicroRM.infrastructure.persistence.sql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "muestra")
public class MuestraJPAEntity {

	@Id
	@Column(name = "id_muestra")
	private UUID idMuestra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_tipo_muestra", nullable = false)
	private TipoMuestraJPAEntity tipoMuestra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_sitio_anatomico", nullable = false)
	private SitioAnatomicoJPAEntity sitioAnatomico;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_estado", nullable = false)
	private EstadoJPAEntity estado;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_colaborador", nullable = false)
	private ColaboradorJPAEntity colaborador;

	@Column(name = "numero_laboratorio", unique = true, length = 10)
	private String numeroLaboratorio;

	@Column(name = "fecha_recepcion")
	private Instant fechaRecepcion;

	@Column(name = "fecha_hora_toma")
	private Instant fechaHoraToma;

	@Column(name = "observaciones_laboratorio", columnDefinition = "text")
	private String observacionesLaboratorio;

	@Column(name = "usuario_registra", length = 100)
	private String usuarioRegistra;

	@Column(name = "fecha_creacion", nullable = false)
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private Instant fechaActualizacion;

	@Column(name = "usuario_crea", length = 100)
	private String usuarioCrea;

	@Column(name = "usuario_actualiza", length = 100)
	private String usuarioActualiza;

	@PrePersist
	void onCreate() {
		Instant now = Instant.now();
		if (fechaCreacion == null) {
			fechaCreacion = now;
		}
		if (fechaRecepcion == null) {
			fechaRecepcion = now;
		}
		fechaActualizacion = now;
	}

	@PreUpdate
	void onUpdate() {
		fechaActualizacion = Instant.now();
	}

	public UUID getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(UUID idMuestra) {
		this.idMuestra = idMuestra;
	}

	public TipoMuestraJPAEntity getTipoMuestra() {
		return tipoMuestra;
	}

	public void setTipoMuestra(TipoMuestraJPAEntity tipoMuestra) {
		this.tipoMuestra = tipoMuestra;
	}

	public SitioAnatomicoJPAEntity getSitioAnatomico() {
		return sitioAnatomico;
	}

	public void setSitioAnatomico(SitioAnatomicoJPAEntity sitioAnatomico) {
		this.sitioAnatomico = sitioAnatomico;
	}

	public EstadoJPAEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoJPAEntity estado) {
		this.estado = estado;
	}

	public ColaboradorJPAEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorJPAEntity colaborador) {
		this.colaborador = colaborador;
	}

	public String getNumeroLaboratorio() {
		return numeroLaboratorio;
	}

	public void setNumeroLaboratorio(String numeroLaboratorio) {
		this.numeroLaboratorio = numeroLaboratorio;
	}

	public Instant getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Instant fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Instant getFechaHoraToma() {
		return fechaHoraToma;
	}

	public void setFechaHoraToma(Instant fechaHoraToma) {
		this.fechaHoraToma = fechaHoraToma;
	}

	public String getObservacionesLaboratorio() {
		return observacionesLaboratorio;
	}

	public void setObservacionesLaboratorio(String observacionesLaboratorio) {
		this.observacionesLaboratorio = observacionesLaboratorio;
	}

	public String getUsuarioRegistra() {
		return usuarioRegistra;
	}

	public void setUsuarioRegistra(String usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
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
