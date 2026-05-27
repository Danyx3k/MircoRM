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
@Table(name = "muestras")
public class MuestraJPAEntity {

	@Id
	@Column(name = "id_muestra")
	private UUID idMuestra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_paciente", nullable = false)
	private PacienteJPAEntity paciente;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_tipo_muestra", nullable = false)
	private TipoMuestraJPAEntity tipoMuestra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_medio_cultivo", nullable = false)
	private MedioCultivoJPAEntity medioCultivo;

	@Column(name = "numero_laboratorio", nullable = false, unique = true, length = 40)
	private String numeroLaboratorio;

	@Column(name = "origen_anatomico", nullable = false, length = 200)
	private String origenAnatomico;

	@Column(name = "fecha_hora_toma", nullable = false)
	private Instant fechaHoraToma;

	@Column(name = "fecha_hora_recepcion")
	private Instant fechaHoraRecepcion;

	@Column(name = "fecha_hora_procesamiento")
	private Instant fechaHoraProcesamiento;

	@Column(nullable = false, length = 30)
	private String estado;

	@Column(name = "cantidad_morfotipos_bacterianos", nullable = false)
	private Integer cantidadMorfotiposBacterianos;

	@Column(name = "es_contaminada", nullable = false)
	private Boolean esContaminada = false;

	@Column(name = "observaciones_clinicas", columnDefinition = "text")
	private String observacionesClinicas;

	@Column(name = "observaciones_laboratorio", columnDefinition = "text")
	private String observacionesLaboratorio;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_colaborador_registra", nullable = false)
	private ColaboradorJPAEntity colaboradorRegistra;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_colaborador_procesa", nullable = false)
	private ColaboradorJPAEntity colaboradorProcesa;

	@Column(name = "usuario_registra", length = 120)
	private String usuarioRegistra;

	@Column(name = "fecha_creacion", nullable = false)
	private Instant fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private Instant fechaActualizacion;

	@Column(name = "usuario_actualiza", length = 120)
	private String usuarioActualiza;

	@PrePersist
	void onCreate() {
		Instant now = Instant.now();
		if (fechaCreacion == null) {
			fechaCreacion = now;
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

	public PacienteJPAEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteJPAEntity paciente) {
		this.paciente = paciente;
	}

	public TipoMuestraJPAEntity getTipoMuestra() {
		return tipoMuestra;
	}

	public void setTipoMuestra(TipoMuestraJPAEntity tipoMuestra) {
		this.tipoMuestra = tipoMuestra;
	}

	public MedioCultivoJPAEntity getMedioCultivo() {
		return medioCultivo;
	}

	public void setMedioCultivo(MedioCultivoJPAEntity medioCultivo) {
		this.medioCultivo = medioCultivo;
	}

	public String getNumeroLaboratorio() {
		return numeroLaboratorio;
	}

	public void setNumeroLaboratorio(String numeroLaboratorio) {
		this.numeroLaboratorio = numeroLaboratorio;
	}

	public String getOrigenAnatomico() {
		return origenAnatomico;
	}

	public void setOrigenAnatomico(String origenAnatomico) {
		this.origenAnatomico = origenAnatomico;
	}

	public Instant getFechaHoraToma() {
		return fechaHoraToma;
	}

	public void setFechaHoraToma(Instant fechaHoraToma) {
		this.fechaHoraToma = fechaHoraToma;
	}

	public Instant getFechaHoraRecepcion() {
		return fechaHoraRecepcion;
	}

	public void setFechaHoraRecepcion(Instant fechaHoraRecepcion) {
		this.fechaHoraRecepcion = fechaHoraRecepcion;
	}

	public Instant getFechaHoraProcesamiento() {
		return fechaHoraProcesamiento;
	}

	public void setFechaHoraProcesamiento(Instant fechaHoraProcesamiento) {
		this.fechaHoraProcesamiento = fechaHoraProcesamiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCantidadMorfotiposBacterianos() {
		return cantidadMorfotiposBacterianos;
	}

	public void setCantidadMorfotiposBacterianos(Integer cantidadMorfotiposBacterianos) {
		this.cantidadMorfotiposBacterianos = cantidadMorfotiposBacterianos;
	}

	public Boolean getEsContaminada() {
		return esContaminada;
	}

	public void setEsContaminada(Boolean esContaminada) {
		this.esContaminada = esContaminada;
	}

	public String getObservacionesClinicas() {
		return observacionesClinicas;
	}

	public void setObservacionesClinicas(String observacionesClinicas) {
		this.observacionesClinicas = observacionesClinicas;
	}

	public String getObservacionesLaboratorio() {
		return observacionesLaboratorio;
	}

	public void setObservacionesLaboratorio(String observacionesLaboratorio) {
		this.observacionesLaboratorio = observacionesLaboratorio;
	}

	public ColaboradorJPAEntity getColaboradorRegistra() {
		return colaboradorRegistra;
	}

	public void setColaboradorRegistra(ColaboradorJPAEntity colaboradorRegistra) {
		this.colaboradorRegistra = colaboradorRegistra;
	}

	public ColaboradorJPAEntity getColaboradorProcesa() {
		return colaboradorProcesa;
	}

	public void setColaboradorProcesa(ColaboradorJPAEntity colaboradorProcesa) {
		this.colaboradorProcesa = colaboradorProcesa;
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

	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
}
