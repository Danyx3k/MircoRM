package co.hospital.MicroRM.infrastructure.persistence.sql;

import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MuestraJpaRepository extends JpaRepository<MuestraJPAEntity, UUID> {

	@Query("""
			SELECT m FROM MuestraJPAEntity m
			JOIN FETCH m.tipoMuestra
			JOIN FETCH m.sitioAnatomico
			JOIN FETCH m.estado
			JOIN MuestraPacienteJPAEntity mp ON mp.muestra = m
			WHERE mp.paciente.idPaciente = :idPaciente
			ORDER BY m.fechaCreacion DESC
			""")
	List<MuestraJPAEntity> findByPacienteIdOrderByFechaCreacionDesc(@Param("idPaciente") UUID idPaciente);

	@Query("SELECT m.numeroLaboratorio FROM MuestraJPAEntity m WHERE m.idMuestra = :id")
	Optional<String> findNumeroLaboratorioById(@Param("id") UUID id);

	boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio);

	boolean existsByNumeroLaboratorioIgnoreCaseAndIdMuestraNot(String numeroLaboratorio, UUID idMuestra);
}
