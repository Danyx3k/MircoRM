package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.EpsJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EpsJpaRepository extends JpaRepository<EpsJPAEntity, UUID> {

	Optional<EpsJPAEntity> findByCodigoIgnoreCase(String codigo);
}
