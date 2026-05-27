package co.hospital.MicroRM.infrastructure.persistence.mapper;

import co.hospital.MicroRM.features.paciente.registernewpatient.application.usecase.RegisterNewPatientDomain;
import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterNewPatientDomainToPacienteEntityMapper {

	PacienteEntity toEntity(RegisterNewPatientDomain domain);
}
