package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MedioCultivoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedioCultivoJpaRepository extends JpaRepository<MedioCultivoJPAEntity, UUID> {
}
