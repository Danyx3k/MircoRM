package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.infrastructure.persistence.repository.ColaboradorRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ColaboradorRepositoryJpaAdapter implements ColaboradorRepository {

	private final ColaboradorJpaRepository jpaRepository;

	public ColaboradorRepositoryJpaAdapter(ColaboradorJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public boolean existsActivoById(UUID idColaborador) {
		return jpaRepository.existsByIdColaboradorAndActivoTrue(idColaborador);
	}
}
