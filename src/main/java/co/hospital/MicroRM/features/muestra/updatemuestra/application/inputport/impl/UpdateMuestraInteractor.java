package co.hospital.MicroRM.features.muestra.updatemuestra.application.inputport.impl;

import co.hospital.MicroRM.features.muestra.updatemuestra.application.inputport.UpdateMuestraInputPort;
import co.hospital.MicroRM.features.muestra.updatemuestra.application.usecase.UpdateMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMuestraInteractor implements UpdateMuestraInputPort {

	private final UpdateMuestraUseCase useCase;

	public UpdateMuestraInteractor(UpdateMuestraUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public MuestraEntity execute(UUID id, RegisterMuestraRequest request) {
		return useCase.execute(id, request);
	}
}
