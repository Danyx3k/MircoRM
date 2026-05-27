package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.PacienteJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteJpaRepository extends JpaRepository<PacienteJPAEntity, UUID> {

	@Query("""
			SELECT DISTINCT p FROM PacienteJPAEntity p
			JOIN MuestraPacienteJPAEntity mp ON mp.paciente = p
			ORDER BY p.apellido ASC, p.nombre ASC
			""")
	List<PacienteJPAEntity> findAllWithMuestrasOrderByApellidoAscNombreAsc();

	Optional<PacienteJPAEntity> findByNumeroIdentificacionIgnoreCase(String numeroIdentificacion);

	boolean existsByNumeroIdentificacionIgnoreCase(String numeroIdentificacion);

	boolean existsByNumeroIdentificacionIgnoreCaseAndIdPacienteNot(String numeroIdentificacion, UUID idPaciente);
}
