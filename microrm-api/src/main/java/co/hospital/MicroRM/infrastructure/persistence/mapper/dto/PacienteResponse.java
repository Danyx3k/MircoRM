package co.hospital.MicroRM.infrastructure.persistence.mapper.dto;

import co.hospital.MicroRM.crossscutting.helper.PacienteEdadCalculator;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.PacienteJPAEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Paciente (lectura)")
public record PacienteResponse(
		UUID id,
		String numeroIdentificacion,
		String codigoTipoDocumento,
		String nombre,
		String apellido,
		LocalDate fechaNacimiento,
		Integer edad,
		String sexo,
		String codigoEps,
		String celular,
		String correo,
		String observacionClinica,
		Instant fechaCreacion,
		Instant fechaActualizacion) {

	public static PacienteResponse from(co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity e, Clock clock) {
		return new PacienteResponse(
				e.getId(),
				e.getNumeroIdentificacion(),
				null,
				e.getNombre(),
				e.getApellido(),
				e.getFechaNacimiento(),
				PacienteEdadCalculator.edadEnAnios(e.getFechaNacimiento(), clock),
				null,
				null,
				e.getCelular(),
				e.getCorreo(),
				e.getObservacionClinica(),
				e.getFechaCreacion(),
				e.getFechaActualizacion());
	}

	public static PacienteResponse fromJpa(PacienteJPAEntity jpa, Clock clock) {
		LocalDate fechaNacimiento = jpa.getFechaNacimiento();
		return new PacienteResponse(
				jpa.getIdPaciente(),
				jpa.getNumeroIdentificacion(),
				jpa.getTipoDocumento().getCodigo(),
				jpa.getNombre(),
				jpa.getApellido(),
				fechaNacimiento,
				PacienteEdadCalculator.edadEnAnios(fechaNacimiento, clock),
				jpa.getSexo().getNombre(),
				jpa.getEps().getCodigo(),
				jpa.getCelular(),
				jpa.getCorreo(),
				jpa.getObservacionClinica(),
				jpa.getFechaCreacion(),
				jpa.getFechaActualizacion());
	}
}
