package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.SitioAnatomicoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SitioAnatomicoJpaRepository extends JpaRepository<SitioAnatomicoJPAEntity, UUID> {

	List<SitioAnatomicoJPAEntity> findAllByOrderByNombreAsc();

	List<SitioAnatomicoJPAEntity> findTop50ByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);
}
