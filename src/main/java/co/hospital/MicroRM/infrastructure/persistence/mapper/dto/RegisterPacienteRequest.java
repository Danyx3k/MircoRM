package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Registro de paciente alineado con {@code microlab.paciente}.
 */
public record RegisterPacienteRequest(
		@NotBlank @Size(max = 20) String numeroIdentificacion,
		@NotBlank @Size(max = 100) String nombre,
		@NotBlank @Size(max = 100) String apellido,
		@NotNull @PastOrPresent LocalDate fechaNacimiento,
		@NotBlank @Size(max = 10) String codigoTipoDocumento,
		@NotBlank @Size(max = 100) String sexo,
		@NotBlank @Size(max = 10) String codigoEps,
		@Size(max = 15) String celular,
		@Email @Size(max = 100) String correo,
		@Size(max = 8000) String observacionClinica) {
}
