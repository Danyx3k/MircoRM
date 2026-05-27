package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.EstadoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstadoJpaRepository extends JpaRepository<EstadoJPAEntity, UUID> {

	Optional<EstadoJPAEntity> findByNombreIgnoreCase(String nombre);
}
