package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jpa;

import co.hospital.MicroRM.infrastructure.persistence.entity.MuestraEntity;
import co.hospital.MicroRM.infrastructure.persistence.mapper.MuestraEntityMapper;
import co.hospital.MicroRM.infrastructure.persistence.repository.MuestraRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.MedioCultivoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.MuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MuestraRepositoryJpaAdapter implements MuestraRepository {

	private final MuestraJpaRepository muestraJpaRepository;
	private final PacienteJpaRepository pacienteJpaRepository;
	private final TipoMuestraJpaRepository tipoMuestraJpaRepository;
	private final MedioCultivoJpaRepository medioCultivoJpaRepository;
	private final ColaboradorJpaRepository colaboradorJpaRepository;
	private final MuestraEntityMapper muestraEntityMapper;

	public MuestraRepositoryJpaAdapter(
			MuestraJpaRepository muestraJpaRepository,
			PacienteJpaRepository pacienteJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository,
			MedioCultivoJpaRepository medioCultivoJpaRepository,
			ColaboradorJpaRepository colaboradorJpaRepository,
			MuestraEntityMapper muestraEntityMapper) {
		this.muestraJpaRepository = muestraJpaRepository;
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
		this.medioCultivoJpaRepository = medioCultivoJpaRepository;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.muestraEntityMapper = muestraEntityMapper;
	}

	@Override
	public void create(MuestraEntity entity) {
		var jpa = muestraEntityMapper.toJpaScalars(entity);
		jpa.setPaciente(pacienteJpaRepository.getReferenceById(entity.getIdPaciente()));
		jpa.setTipoMuestra(tipoMuestraJpaRepository.getReferenceById(entity.getIdTipoMuestra()));
		jpa.setMedioCultivo(medioCultivoJpaRepository.getReferenceById(entity.getIdMedioCultivo()));
		var colReg = colaboradorJpaRepository.getReferenceById(entity.getIdColaboradorRegistra());
		var colProc = colaboradorJpaRepository.getReferenceById(entity.getIdColaboradorProcesa());
		jpa.setColaboradorRegistra(colReg);
		jpa.setColaboradorProcesa(colProc);
		jpa.setUsuarioRegistra(colReg.getUsername());
		muestraJpaRepository.save(jpa);
	}

	@Override
	public boolean existsByNumeroLaboratorioIgnoreCase(String numeroLaboratorio) {
		return muestraJpaRepository.existsByNumeroLaboratorioIgnoreCase(numeroLaboratorio);
	}
}
