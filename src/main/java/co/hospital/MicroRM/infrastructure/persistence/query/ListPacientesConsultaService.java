package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.MuestraResumenResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteConMuestrasResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteResumenResponse;
import co.hospital.MicroRM.infrastructure.persistence.sql.MuestraJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPacientesConsultaService {

	private final PacienteJpaRepository pacienteJpaRepository;
	private final MuestraJpaRepository muestraJpaRepository;

	public ListPacientesConsultaService(
			PacienteJpaRepository pacienteJpaRepository,
			MuestraJpaRepository muestraJpaRepository) {
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.muestraJpaRepository = muestraJpaRepository;
	}

	public List<PacienteResumenResponse> listarPacientes() {
		return pacienteJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "apellido", "nombre")).stream()
				.map(p -> new PacienteResumenResponse(
						p.getIdPaciente(),
						p.getNumeroIdentificacion(),
						p.getNombre(),
						p.getApellido()))
				.toList();
	}

	public List<PacienteConMuestrasResponse> listarPacientesConMuestras() {
		return pacienteJpaRepository.findAllWithMuestrasOrderByApellidoAscNombreAsc().stream()
				.map(p -> {
					var resumen = new PacienteResumenResponse(
							p.getIdPaciente(),
							p.getNumeroIdentificacion(),
							p.getNombre(),
							p.getApellido());
					var muestras = muestraJpaRepository
							.findByPacienteIdOrderByFechaCreacionDesc(p.getIdPaciente())
							.stream()
							.map(m -> new MuestraResumenResponse(
									m.getIdMuestra(),
									m.getNumeroLaboratorio(),
									m.getTipoMuestra().getNombre(),
									m.getEstado().getNombre(),
									m.getSitioAnatomico().getNombre(),
									m.getFechaHoraToma()))
							.toList();
					return new PacienteConMuestrasResponse(resumen, muestras);
				})
				.filter(r -> !r.muestras().isEmpty())
				.toList();
	}
}
