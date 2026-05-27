package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Paciente encontrado por documento")
public record PacienteBusquedaResponse(
		UUID id,
		String numeroIdentificacion,
		String nombre,
		String apellido,
		LocalDate fechaNacimiento,
		Integer edad,
		String sexo,
		String observacionClinica) {
}
