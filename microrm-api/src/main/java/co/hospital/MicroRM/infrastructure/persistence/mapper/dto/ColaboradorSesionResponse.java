package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import java.util.UUID;

public record ColaboradorSesionResponse(
		UUID idColaborador,
		String nombre,
		String apellido,
		String nombreCompleto,
		String correoElectronico) {
}
