package co.hospital.MicroRM.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

public class MuestraEntity {

	private UUID id;
	private UUID idPaciente;
	private UUID idTipoMuestra;
	private UUID idSitioAnatomico;
	private UUID idEstado;
	private UUID idColaborador;
	private String numeroLaboratorio;
	private String tipoMuestraNombre;
	private String sitioAnatomicoNombre;
	private String estadoNombre;
	private Instant fechaRecepcion;
	private Instant fechaHoraToma;
	private String observacionesLaboratorio;
	private Instant fechaCreacion;
	private Instant fechaActualizacion;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(UUID idPaciente) {
		this.idPaciente = idPaciente;
	}

	public UUID getIdSitioAnatomico() {
		return idSitioAnatomico;
	}

	public UUID getIdTipoMuestra() {
		return idTipoMuestra;
	}

	public void setIdTipoMuestra(UUID idTipoMuestra) {
		this.idTipoMuestra = idTipoMuestra;
	}

	public void setIdSitioAnatomico(UUID idSitioAnatomico) {
		this.idSitioAnatomico = idSitioAnatomico;
	}

	public UUID getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(UUID idEstado) {
		this.idEstado = idEstado;
	}

	public UUID getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(UUID idColaborador) {
		this.idColaborador = idColaborador;
	}

	public String getNumeroLaboratorio() {
		return numeroLaboratorio;
	}

	public void setNumeroLaboratorio(String numeroLaboratorio) {
		this.numeroLaboratorio = numeroLaboratorio;
	}

	public String getTipoMuestraNombre() {
		return tipoMuestraNombre;
	}

	public void setTipoMuestraNombre(String tipoMuestraNombre) {
		this.tipoMuestraNombre = tipoMuestraNombre;
	}

	public String getSitioAnatomicoNombre() {
		return sitioAnatomicoNombre;
	}

	public void setSitioAnatomicoNombre(String sitioAnatomicoNombre) {
		this.sitioAnatomicoNombre = sitioAnatomicoNombre;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
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
