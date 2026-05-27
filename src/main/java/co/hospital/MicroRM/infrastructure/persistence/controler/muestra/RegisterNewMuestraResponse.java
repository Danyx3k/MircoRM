package co.hospital.MicroRM.infrastructure.persistence.controler.muestra;

import java.util.UUID;

public record RegisterNewMuestraResponse(String codigo, String mensaje, UUID idMuestra) {
}
