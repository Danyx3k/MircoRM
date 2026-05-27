package co.hospital.MicroRM.infrastructure.persistence.mapper;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.PacienteJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct: capa de persistencia (entidad de dominio ↔ JPA), alineado con {@code StudentMapperJPA} en UcoParking.
 */
@Mapper(componentModel = "spring")
public interface PacienteJpaEntityMapper {

	@Mapping(target = "idPaciente", source = "id")
	PacienteJPAEntity toJpa(PacienteEntity entity);

	@Mapping(target = "id", source = "idPaciente")
	PacienteEntity toDomain(PacienteJPAEntity jpa);
}
