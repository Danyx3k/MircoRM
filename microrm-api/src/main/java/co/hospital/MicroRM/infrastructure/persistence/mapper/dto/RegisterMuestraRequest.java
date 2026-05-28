package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

/**
 * Captura inicial de muestra ({@code microlab.muestra} + {@code muestra_paciente}).
 */
public record RegisterMuestraRequest(
		@NotNull UUID idPaciente,
		@NotNull UUID idTipoMuestra,
		@NotNull UUID idSitioAnatomico,
		@NotNull Instant fechaHoraToma) {
}
