package co.hospital.MicroRM.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

public class MuestraEntity {

	private UUID id;
	private UUID idPaciente;
	private UUID idTipoMuestra;
	private UUID idMedioCultivo;
	private String numeroLaboratorio;
	private String origenAnatomico;
	private Instant fechaHoraToma;
	private Instant fechaHoraRecepcion;
	private String estado;
	private Integer cantidadMorfotiposBacterianos;
	private String observaciones;
	private UUID idColaboradorRegistra;
	private UUID idColaboradorProcesa;
	private Boolean esContaminada;
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

	public UUID getIdTipoMuestra() {
		return idTipoMuestra;
	}

	public void setIdTipoMuestra(UUID idTipoMuestra) {
		this.idTipoMuestra = idTipoMuestra;
	}

	public UUID getIdMedioCultivo() {
		return idMedioCultivo;
	}

	public void setIdMedioCultivo(UUID idMedioCultivo) {
		this.idMedioCultivo = idMedioCultivo;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public UUID getIdColaboradorRegistra() {
		return idColaboradorRegistra;
	}

	public void setIdColaboradorRegistra(UUID idColaboradorRegistra) {
		this.idColaboradorRegistra = idColaboradorRegistra;
	}

	public UUID getIdColaboradorProcesa() {
		return idColaboradorProcesa;
	}

	public void setIdColaboradorProcesa(UUID idColaboradorProcesa) {
		this.idColaboradorProcesa = idColaboradorProcesa;
	}

	public Boolean getEsContaminada() {
		return esContaminada;
	}

	public void setEsContaminada(Boolean esContaminada) {
		this.esContaminada = esContaminada;
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
