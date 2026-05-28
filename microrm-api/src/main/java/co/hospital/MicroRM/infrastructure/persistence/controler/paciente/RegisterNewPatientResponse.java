package co.hospital.MicroRM.infrastructure.persistence.controler.paciente;

import java.util.UUID;

public record RegisterNewPatientResponse(String codigo, String mensaje, UUID idPaciente) {
}
