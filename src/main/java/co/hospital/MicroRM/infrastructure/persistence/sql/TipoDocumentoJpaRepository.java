package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.TipoDocumentoJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TipoDocumentoJpaRepository extends JpaRepository<TipoDocumentoJPAEntity, UUID> {

	Optional<TipoDocumentoJPAEntity> findByCodigoIgnoreCase(String codigo);
}
