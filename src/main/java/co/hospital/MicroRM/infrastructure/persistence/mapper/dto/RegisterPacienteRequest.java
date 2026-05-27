package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * JSON: {@code fechaNacimiento} como fecha ISO para PostgreSQL DATE: {@code "yyyy-MM-dd"} (ej. {@code "1998-03-15"}).
 */
public record RegisterPacienteRequest(
		@NotBlank
		@Size(max = 15)
		@Pattern(regexp = "^[A-Za-z0-9]+$", message = "La identificación debe ser alfanumérica (máx. 15 caracteres)")
		String identificacion,
		@NotBlank
		@Size(max = 20)
		String nombre,
		@NotBlank
		@Size(max = 20)
		String apellido,
		@NotNull
		@PastOrPresent
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		LocalDate fechaNacimiento,
		@NotBlank
		@Pattern(regexp = "(?i)^(MASCULINO|FEMENINO|OTRO)$", message = "Género: MASCULINO, FEMENINO u OTRO")
		String genero,
		@Size(max = 30) String telefono,
		@NotBlank
		@Size(max = 160)
		@Pattern(
				regexp = "(?i)^[A-Za-z0-9._-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.(com|co)$",
				message = "Correo: parte local alfanumérica, @, dominio y TLD .com o .co")
		String email,
		@NotBlank
		@Size(max = 120)
		@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "La EPS debe ser alfanumérica (se permiten espacios)")
		String epsSeguro,
		@Size(max = 5000) String observacionesClinicas
) {
}
