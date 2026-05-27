package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MuestraJpaRepository extends JpaRepository<MuestraJPAEntity, UUID> {

	boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio);
}
