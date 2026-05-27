package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.RolColaboradorJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolColaboradorJpaRepository extends JpaRepository<RolColaboradorJPAEntity, UUID> {
}
