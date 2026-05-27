package co.hospital.MicroRM.infrastructure.persistence.mapper;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct: muestra dominio ↔ JPA y DTO de registro ↔ dominio.
 */
@Mapper(componentModel = "spring")
public interface MuestraEntityMapper {

	@Mapping(target = "id", source = "idMuestra")
	@Mapping(target = "idPaciente", source = "paciente.idPaciente")
	@Mapping(target = "idTipoMuestra", source = "tipoMuestra.idTipoMuestra")
	@Mapping(target = "idMedioCultivo", source = "medioCultivo.idMedioCultivo")
	@Mapping(target = "idColaboradorRegistra", source = "colaboradorRegistra.idColaborador")
	@Mapping(target = "idColaboradorProcesa", source = "colaboradorProcesa.idColaborador")
	MuestraEntity toDomain(MuestraJPAEntity jpa);

	@Mapping(target = "paciente", ignore = true)
	@Mapping(target = "tipoMuestra", ignore = true)
	@Mapping(target = "medioCultivo", ignore = true)
	@Mapping(target = "colaboradorRegistra", ignore = true)
	@Mapping(target = "colaboradorProcesa", ignore = true)
	@Mapping(target = "idMuestra", source = "id")
	MuestraJPAEntity toJpaScalars(MuestraEntity entity);

	MuestraEntity toDomain(RegisterMuestraRequest request);

	RegisterMuestraRequest toRequest(MuestraEntity entity);
}
