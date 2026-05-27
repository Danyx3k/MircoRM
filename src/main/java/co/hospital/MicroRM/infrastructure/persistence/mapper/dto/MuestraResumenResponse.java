package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import java.time.Instant;
import java.util.UUID;

public record MuestraResumenResponse(
		UUID id,
		String numeroLaboratorio,
		String tipoMuestra,
		String estado,
		String sitioAnatomico,
		Instant fechaHoraToma) {
}
