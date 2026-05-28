package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteBusquedaResponse;
import co.hospital.MicroRM.crossscutting.helper.PacienteEdadCalculator;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
public class BuscarPacientePorIdentificacionService {

	private final PacienteJpaRepository pacienteJpaRepository;
	private final Clock microRmClock;

	public BuscarPacientePorIdentificacionService(
			PacienteJpaRepository pacienteJpaRepository,
			Clock microRmClock) {
		this.pacienteJpaRepository = pacienteJpaRepository;
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
		return new PacienteBusquedaResponse(
				p.getIdPaciente(),
				p.getNumeroIdentificacion(),
				p.getNombre(),
				p.getApellido(),
				p.getFechaNacimiento(),
				PacienteEdadCalculator.edadEnAnios(p.getFechaNacimiento(), microRmClock),
				p.getSexo().getNombre(),
				p.getObservacionClinica());
	}
}
