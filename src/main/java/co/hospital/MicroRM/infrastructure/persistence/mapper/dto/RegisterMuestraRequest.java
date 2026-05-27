package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record RegisterMuestraRequest(
		@NotNull UUID idPaciente,
		@NotNull UUID idTipoMuestra,
		@NotNull UUID idMedioCultivo,
		@NotBlank @Size(max = 200) String origenAnatomico,
		@NotNull Instant fechaHoraToma,
		Instant fechaHoraRecepcion,
		Instant fechaHoraProcesamiento,
		@NotBlank @Size(max = 30) String estado,
		@NotNull @Min(0) @Max(9999) Integer cantidadMorfotiposBacterianos,
		@Size(max = 8000) String observacionesClinicas,
		@Size(max = 8000) String observacionesLaboratorio,
		@NotNull UUID idColaboradorRegistra,
		@NotNull UUID idColaboradorProcesa,
		@NotNull Boolean esContaminada
) {
}
