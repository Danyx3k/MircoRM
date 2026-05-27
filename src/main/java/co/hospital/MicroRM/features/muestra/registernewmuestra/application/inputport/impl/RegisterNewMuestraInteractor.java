package co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.impl;

import co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.RegisterNewMuestraInputPort;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.RegisterNewMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.mapper.MuestraEntityMapper;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterNewMuestraInteractor implements RegisterNewMuestraInputPort {

	private final RegisterNewMuestraUseCase useCase;
	private final MuestraEntityMapper muestraEntityMapper;

	public RegisterNewMuestraInteractor(RegisterNewMuestraUseCase useCase, MuestraEntityMapper muestraEntityMapper) {
		this.useCase = useCase;
		this.muestraEntityMapper = muestraEntityMapper;
	}

	@Override
	public UUID execute(RegisterMuestraRequest data) {
		return useCase.execute(muestraEntityMapper.toDomain(data));
	}
}
