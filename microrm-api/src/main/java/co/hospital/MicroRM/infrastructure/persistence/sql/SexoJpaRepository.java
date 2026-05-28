package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.SexoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SexoJpaRepository extends JpaRepository<SexoJPAEntity, UUID> {

	Optional<SexoJPAEntity> findByNombreIgnoreCase(String nombre);
}
