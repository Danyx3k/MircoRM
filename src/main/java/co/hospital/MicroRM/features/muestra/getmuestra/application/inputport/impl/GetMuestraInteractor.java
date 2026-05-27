package co.hospital.MicroRM.features.muestra.getmuestra.application.inputport.impl;

import co.hospital.MicroRM.features.muestra.getmuestra.application.inputport.GetMuestraInputPort;
import co.hospital.MicroRM.features.muestra.getmuestra.application.usecase.GetMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetMuestraInteractor implements GetMuestraInputPort {

	private final GetMuestraUseCase useCase;

	public GetMuestraInteractor(GetMuestraUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public MuestraEntity execute(UUID id) {
		return useCase.execute(id);
	}
}
