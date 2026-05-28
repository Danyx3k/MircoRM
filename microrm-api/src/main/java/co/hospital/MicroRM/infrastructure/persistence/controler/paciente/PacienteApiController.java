package co.hospital.MicroRM.infrastructure.persistence.controler.paciente;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessageCatalog;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.inputport.RegisterNewPatientInputPort;
import co.hospital.MicroRM.features.paciente.updatepatient.application.inputport.UpdatePacienteInputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteBusquedaResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteConMuestrasResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.PacienteResumenResponse;
import co.hospital.MicroRM.infrastructure.persistence.query.BuscarPacientePorIdentificacionService;
import co.hospital.MicroRM.infrastructure.persistence.query.ListPacientesConsultaService;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;
import co.hospital.MicroRM.infrastructure.persistence.sql.PacienteJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.util.UUID;

@Tag(name = "Pacientes", description = "Listados (GET /lista, GET /con-muestras), registro (POST), consulta por id (GET) y actualización (PUT)")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteApiController {

	private final RegisterNewPatientInputPort registerNewPatientInputPort;
	private final UpdatePacienteInputPort updatePacienteInputPort;
	private final ListPacientesConsultaService listPacientesConsultaService;
	private final BuscarPacientePorIdentificacionService buscarPacientePorIdentificacionService;
	private final PacienteJpaRepository pacienteJpaRepository;
	private final MessageCatalog messageCatalog;
	private final Clock microRmClock;

	public PacienteApiController(
			RegisterNewPatientInputPort registerNewPatientInputPort,
			UpdatePacienteInputPort updatePacienteInputPort,
			ListPacientesConsultaService listPacientesConsultaService,
			BuscarPacientePorIdentificacionService buscarPacientePorIdentificacionService,
			PacienteJpaRepository pacienteJpaRepository,
			MessageCatalog messageCatalog,
			Clock microRmClock) {
		this.registerNewPatientInputPort = registerNewPatientInputPort;
		this.updatePacienteInputPort = updatePacienteInputPort;
		this.listPacientesConsultaService = listPacientesConsultaService;
		this.buscarPacientePorIdentificacionService = buscarPacientePorIdentificacionService;
		this.pacienteJpaRepository = pacienteJpaRepository;
		this.messageCatalog = messageCatalog;
		this.microRmClock = microRmClock;
	}

	@Operation(summary = "Listar pacientes (resumen)", description = "Ordenados por apellido y nombre. Útil para selects y pantallas de listado.")
	@GetMapping("/lista")
	public ResponseEntity<java.util.List<PacienteResumenResponse>> listarResumen() {
		return ResponseEntity.ok(listPacientesConsultaService.listarPacientes());
	}

	@Operation(summary = "Pacientes con muestras", description = "Solo pacientes con al menos una muestra registrada. Cada fila incluye sus muestras con número de laboratorio consecutivo único.")
	@GetMapping("/con-muestras")
	public ResponseEntity<java.util.List<PacienteConMuestrasResponse>> listarConMuestras() {
		return ResponseEntity.ok(listPacientesConsultaService.listarPacientesConMuestras());
	}

	@Operation(summary = "Registrar paciente", description = "Crea un paciente. La identificación debe ser única.")
	@PostMapping
	public ResponseEntity<RegisterNewPatientResponse> registrar(@Valid @RequestBody RegisterPacienteRequest request) {
		UUID id = registerNewPatientInputPort.execute(request);
		String mensaje = messageCatalog.getUserMessage(MessagesEnum.PACIENTE_REGISTRADO_OK, LocaleContextHolder.getLocale());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new RegisterNewPatientResponse(MessagesEnum.PACIENTE_REGISTRADO_OK.getCode(), mensaje, id));
	}

	@Operation(summary = "Buscar paciente por documento", description = "Para registro de muestra: devuelve datos demográficos y observación clínica.")
	@GetMapping("/por-identificacion/{identificacion}")
	public ResponseEntity<PacienteBusquedaResponse> buscarPorIdentificacion(@PathVariable String identificacion) {
		return ResponseEntity.ok(buscarPacientePorIdentificacionService.buscar(identificacion));
	}

	@Operation(summary = "Obtener paciente por id")
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponse> obtener(@PathVariable UUID id) {
		var jpa = pacienteJpaRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
		return ResponseEntity.ok(PacienteResponse.fromJpa(jpa, microRmClock));
	}

	@Operation(summary = "Actualizar paciente", description = "Reemplaza los datos del paciente. La identificación no puede estar duplicada en otro registro.")
	@PutMapping("/{id}")
	public ResponseEntity<PacienteResponse> actualizar(
			@PathVariable UUID id,
			@Valid @RequestBody RegisterPacienteRequest request) {
		updatePacienteInputPort.execute(id, request);
		var jpa = pacienteJpaRepository.findById(id)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.PACIENTE_NO_ENCONTRADO));
		return ResponseEntity.ok(PacienteResponse.fromJpa(jpa, microRmClock));
	}
}
