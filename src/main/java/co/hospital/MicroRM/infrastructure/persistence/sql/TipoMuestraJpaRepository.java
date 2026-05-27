package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.TipoMuestraJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoMuestraJpaRepository extends JpaRepository<TipoMuestraJPAEntity, UUID> {
}
