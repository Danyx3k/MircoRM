package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.infrastructure.persistence.repository.TipoMuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TipoMuestraRepositoryJpaAdapter implements TipoMuestraRepository {

	private final TipoMuestraJpaRepository jpaRepository;

	public TipoMuestraRepositoryJpaAdapter(TipoMuestraJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaRepository.existsById(id);
	}
}
