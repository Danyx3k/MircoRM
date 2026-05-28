package co.hospital.MicroRM.infrastructure.messaging.domain;

import java.util.UUID;

public record PacienteRegistradoEvent(UUID idPaciente, String numeroIdentificacion) {
}
