package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.infrastructure.persistence.repository.MedioCultivoRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.MedioCultivoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MedioCultivoRepositoryJpaAdapter implements MedioCultivoRepository {

	private final MedioCultivoJpaRepository jpaRepository;

	public MedioCultivoRepositoryJpaAdapter(MedioCultivoJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaRepository.existsById(id);
	}
}
