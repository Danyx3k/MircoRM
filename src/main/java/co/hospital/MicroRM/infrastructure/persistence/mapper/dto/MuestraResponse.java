package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Muestra de laboratorio (lectura)")
public record MuestraResponse(
		UUID id,
		String numeroLaboratorio,
		UUID idTipoMuestra,
		String tipoMuestra,
		UUID idSitioAnatomico,
		String sitioAnatomico,
		String estado,
		UUID idColaborador,
		Instant fechaRecepcion,
		Instant fechaHoraToma,
		String observacionesLaboratorio,
		Instant fechaCreacion,
		Instant fechaActualizacion) {

	public static MuestraResponse from(co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity e) {
		return new MuestraResponse(
				e.getId(),
				e.getNumeroLaboratorio(),
				e.getIdTipoMuestra(),
				e.getTipoMuestraNombre(),
				e.getIdSitioAnatomico(),
				e.getSitioAnatomicoNombre(),
				e.getEstadoNombre(),
				e.getIdColaborador(),
				e.getFechaRecepcion(),
				e.getFechaHoraToma(),
				e.getObservacionesLaboratorio(),
				e.getFechaCreacion(),
				e.getFechaActualizacion());
	}

	public static MuestraResponse fromJpa(MuestraJPAEntity jpa) {
		return new MuestraResponse(
				jpa.getIdMuestra(),
				jpa.getNumeroLaboratorio(),
				jpa.getTipoMuestra().getIdTipoMuestra(),
				jpa.getTipoMuestra().getNombre(),
				jpa.getSitioAnatomico().getIdSitioAnatomico(),
				jpa.getSitioAnatomico().getNombre(),
				jpa.getEstado().getNombre(),
				jpa.getColaborador().getIdColaborador(),
				jpa.getFechaRecepcion(),
				jpa.getFechaHoraToma(),
				jpa.getObservacionesLaboratorio(),
				jpa.getFechaCreacion(),
				jpa.getFechaActualizacion());
	}
}
