package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.MuestraResumenResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteBusquedaResponse;
import co.hospital.MicroRM.crossscutting.helper.PacienteEdadCalculator;
import co.hospital.MicroRM.infrastructure.persistence.sql.MuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.MuestraJPAEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;

@Service
public class BuscarPacientePorIdentificacionService {

	private final PacienteJpaRepository pacienteJpaRepository;
	private final MuestraJpaRepository muestraJpaRepository;
	private final Clock microRmClock;

	public BuscarPacientePorIdentificacionService(
			PacienteJpaRepository pacienteJpaRepository,
			MuestraJpaRepository muestraJpaRepository,
			Clock microRmClock) {
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.muestraJpaRepository = muestraJpaRepository;
		this.microRmClock = microRmClock;
	}

	@Transactional(readOnly = true)
	public PacienteBusquedaResponse buscar(String identificacion) {
		String doc = identificacion == null ? "" : identificacion.trim();
		if (doc.isEmpty()) {
			throw MicroRMException.of(MessagesEnum.PACIENTE_IDENTIFICACION_REQUERIDA);
		}
		var p = pacienteJpaRepository.findByNumeroIdentificacionIgnoreCase(doc)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
		List<MuestraResumenResponse> muestras = muestraJpaRepository
				.findByPacienteIdOrderByFechaCreacionDesc(p.getIdPaciente())
				.stream()
				.map(this::toMuestraResumen)
				.toList();

		return new PacienteBusquedaResponse(
				p.getIdPaciente(),
				p.getNumeroIdentificacion(),
				p.getNombre(),
				p.getApellido(),
				p.getFechaNacimiento(),
				PacienteEdadCalculator.edadEnAnios(p.getFechaNacimiento(), microRmClock),
				p.getSexo().getNombre(),
				p.getObservacionClinica(),
				muestras);
	}

	private MuestraResumenResponse toMuestraResumen(MuestraJPAEntity m) {
		return new MuestraResumenResponse(
				m.getIdMuestra(),
				m.getNumeroLaboratorio(),
				m.getTipoMuestra().getNombre(),
				m.getEstado().getNombre(),
				m.getSitioAnatomico().getNombre(),
				m.getFechaHoraToma());
	}
}
