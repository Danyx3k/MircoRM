package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraPacienteJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MuestraPacienteJpaRepository extends JpaRepository<MuestraPacienteJPAEntity, UUID> {
}
