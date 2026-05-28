package co.hospital.MicroRM.infrastructure.messaging.domain;

import java.util.UUID;

public record MuestraRegistradaEvent(
		UUID idMuestra,
		UUID idPaciente,
		String numeroLaboratorio) {
}
