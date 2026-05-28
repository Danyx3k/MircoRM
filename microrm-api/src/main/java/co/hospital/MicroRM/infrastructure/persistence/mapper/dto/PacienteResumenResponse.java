package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import java.util.UUID;

public record PacienteResumenResponse(
		UUID id,
		String numeroIdentificacion,
		String nombre,
		String apellido) {
}
