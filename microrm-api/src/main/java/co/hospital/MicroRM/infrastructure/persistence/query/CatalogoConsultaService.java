package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.CatalogoItemResponse;
import co.hospital.MicroRM.infrastructure.persistence.sql.EpsJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SexoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SitioAnatomicoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoDocumentoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoMuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.EpsJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.SexoJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.SitioAnatomicoJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.TipoDocumentoJPAEntity;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.TipoMuestraJPAEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogoConsultaService {

	private final SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository;
	private final TipoDocumentoJpaRepository tipoDocumentoJpaRepository;
	private final SexoJpaRepository sexoJpaRepository;
	private final EpsJpaRepository epsJpaRepository;
	private final TipoMuestraJpaRepository tipoMuestraJpaRepository;

	public CatalogoConsultaService(
			SitioAnatomicoJpaRepository sitioAnatomicoJpaRepository,
			TipoDocumentoJpaRepository tipoDocumentoJpaRepository,
			SexoJpaRepository sexoJpaRepository,
			EpsJpaRepository epsJpaRepository,
			TipoMuestraJpaRepository tipoMuestraJpaRepository) {
		this.sitioAnatomicoJpaRepository = sitioAnatomicoJpaRepository;
		this.tipoDocumentoJpaRepository = tipoDocumentoJpaRepository;
		this.sexoJpaRepository = sexoJpaRepository;
		this.epsJpaRepository = epsJpaRepository;
		this.tipoMuestraJpaRepository = tipoMuestraJpaRepository;
	}

	@Transactional(readOnly = true)
	public List<CatalogoItemResponse> buscarSitiosAnatomicos(String q) {
		List<SitioAnatomicoJPAEntity> rows = isBlank(q)
				? sitioAnatomicoJpaRepository.findAllByOrderByNombreAsc()
				: sitioAnatomicoJpaRepository.findTop50ByNombreContainingIgnoreCaseOrderByNombreAsc(q.trim());
		return rows.stream().map(CatalogoConsultaService::toItem).toList();
	}

	@Transactional(readOnly = true)
	public List<CatalogoItemResponse> buscarTiposMuestra(String q) {
		List<TipoMuestraJPAEntity> rows = isBlank(q)
				? tipoMuestraJpaRepository.findAllByOrderByNombreAsc()
				: tipoMuestraJpaRepository.findTop50ByNombreContainingIgnoreCaseOrderByNombreAsc(q.trim());
		return rows.stream().map(CatalogoConsultaService::toTipoMuestraItem).toList();
	}

	@Transactional(readOnly = true)
	public List<CatalogoItemResponse> listarTiposDocumento() {
		return tipoDocumentoJpaRepository.findAll().stream().map(CatalogoConsultaService::toItem).toList();
	}

	@Transactional(readOnly = true)
	public List<CatalogoItemResponse> listarSexos() {
		return sexoJpaRepository.findAll().stream().map(CatalogoConsultaService::toItem).toList();
	}

	@Transactional(readOnly = true)
	public List<CatalogoItemResponse> listarEps() {
		return epsJpaRepository.findAll().stream().map(CatalogoConsultaService::toItem).toList();
	}

	private static boolean isBlank(String q) {
		return q == null || q.isBlank();
	}

	private static CatalogoItemResponse toItem(SitioAnatomicoJPAEntity e) {
		return new CatalogoItemResponse(e.getIdSitioAnatomico(), e.getNombre(), null);
	}

	private static CatalogoItemResponse toItem(TipoDocumentoJPAEntity e) {
		return new CatalogoItemResponse(e.getIdTipoDocumento(), e.getNombre(), e.getCodigo());
	}

	private static CatalogoItemResponse toItem(SexoJPAEntity e) {
		return new CatalogoItemResponse(e.getIdSexo(), e.getNombre(), null);
	}

	private static CatalogoItemResponse toItem(EpsJPAEntity e) {
		return new CatalogoItemResponse(e.getIdEps(), e.getNombre(), e.getCodigo());
	}

	private static CatalogoItemResponse toTipoMuestraItem(TipoMuestraJPAEntity e) {
		return new CatalogoItemResponse(e.getIdTipoMuestra(), e.getNombre(), null);
	}
}
