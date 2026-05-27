package co.hospital.MicroRM.infrastructure.persistence.repository;

import java.util.UUID;

public interface TipoMuestraRepository {

	boolean existsById(UUID id);
}
