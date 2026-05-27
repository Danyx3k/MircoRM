package co.hospital.MicroRM.infrastructure.persistence.repository;

import java.util.UUID;

public interface MedioCultivoRepository {

	boolean existsById(UUID id);
}
