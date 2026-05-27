package co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.impl;

import co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.RegisterNewMuestraInputPort;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.usecase.domain.RegisterNewMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterNewMuestraInteractor implements RegisterNewMuestraInputPort {

	private final RegisterNewMuestraUseCase useCase;

	public RegisterNewMuestraInteractor(RegisterNewMuestraUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public UUID execute(RegisterMuestraRequest data) {
		MuestraEntity entity = new MuestraEntity();
		entity.setIdPaciente(data.idPaciente());
		entity.setIdTipoMuestra(data.idTipoMuestra());
		entity.setIdSitioAnatomico(data.idSitioAnatomico());
		entity.setFechaHoraToma(data.fechaHoraToma());
		return useCase.execute(entity);
	}
}
