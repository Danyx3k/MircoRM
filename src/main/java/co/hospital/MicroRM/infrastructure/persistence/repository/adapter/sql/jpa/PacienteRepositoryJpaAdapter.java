package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.infrastructure.persistence.entity.PacienteEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.PacienteJpaEntityMapper;
import co.hospital.MicroRM.infrastructure.persistence.repository.PacienteRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PacienteRepositoryJpaAdapter implements PacienteRepository {

	private final PacienteJpaRepository jpaRepository;
	private final PacienteJpaEntityMapper mapper;

	public PacienteRepositoryJpaAdapter(PacienteJpaRepository jpaRepository, PacienteJpaEntityMapper mapper) {
		this.jpaRepository = jpaRepository;
		this.mapper = mapper;
	}

	@Override
	public void create(PacienteEntity entity) {
		jpaRepository.save(mapper.toJpa(entity));
	}

	@Override
	public boolean existsByIdentificacionIgnoreCase(String identificacion) {
		return jpaRepository.existsByIdentificacionIgnoreCase(identificacion);
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaRepository.existsById(id);
	}
}
