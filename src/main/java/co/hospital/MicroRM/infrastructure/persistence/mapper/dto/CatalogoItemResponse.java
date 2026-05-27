package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Ítem de catálogo (tipo de muestra u origen anatómico)")
public record CatalogoItemResponse(
		UUID id,
		String nombre,
		String codigo) {
}
