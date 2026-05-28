package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Paciente con el listado de sus muestras")
public record PacienteConMuestrasResponse(
		PacienteResumenResponse paciente,
		List<MuestraResumenResponse> muestras) {
}
