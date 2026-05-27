package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.ColaboradorJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ColaboradorJpaRepository extends JpaRepository<ColaboradorJPAEntity, UUID> {

	Optional<ColaboradorJPAEntity> findFirstByActivoTrueOrderByFechaCreacionAsc();

	boolean existsByIdColaboradorAndActivoTrue(UUID idColaborador);
}
