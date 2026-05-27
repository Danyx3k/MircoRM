package co.hospital.MicroRM.infrastructure.persistence.repository;

import java.util.UUID;

public interface ColaboradorRepository {

	boolean existsActivoById(UUID idColaborador);
}
