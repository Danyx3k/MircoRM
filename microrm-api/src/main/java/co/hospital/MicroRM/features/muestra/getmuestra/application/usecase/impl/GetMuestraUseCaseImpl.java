package co.hospital.MicroRM.features.muestra.getmuestra.application.usecase.impl;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.getmuestra.application.usecase.GetMuestraUseCase;
import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetMuestraUseCaseImpl implements GetMuestraUseCase {

	private final MuestraRepository muestraRepository;

	public GetMuestraUseCaseImpl(MuestraRepository muestraRepository) {
		this.muestraRepository = muestraRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public MuestraEntity execute(UUID id) {
		return muestraRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_NO_ENCONTRADA));
	}
}
