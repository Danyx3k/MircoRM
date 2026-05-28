package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.TipoMuestraJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TipoMuestraJpaRepository extends JpaRepository<TipoMuestraJPAEntity, UUID> {

	List<TipoMuestraJPAEntity> findAllByOrderByNombreAsc();

	List<TipoMuestraJPAEntity> findTop50ByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);
}
