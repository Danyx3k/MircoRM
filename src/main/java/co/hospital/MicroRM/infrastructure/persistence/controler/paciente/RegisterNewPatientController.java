package co.hospital.MicroRM.infrastructure.persistence.controler.paciente;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessageCatalog;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.paciente.registernewpatient.application.inputport.RegisterNewPatientInputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterPacienteRequest;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
@RestController
@RequestMapping("/api/v1/pacientes")
public class RegisterNewPatientController {

	private final RegisterNewPatientInputPort inputPort;
	private final MessageCatalog messageCatalog;

	public RegisterNewPatientController(RegisterNewPatientInputPort inputPort, MessageCatalog messageCatalog) {
		this.inputPort = inputPort;
		this.messageCatalog = messageCatalog;
	}

	@PostMapping
	public ResponseEntity<RegisterNewPatientResponse> register(@Valid @RequestBody RegisterPacienteRequest request) {
		UUID id = inputPort.execute(request);
		String mensaje = messageCatalog.getUserMessage(MessagesEnum.PACIENTE_REGISTRADO_OK, LocaleContextHolder.getLocale());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new RegisterNewPatientResponse(MessagesEnum.PACIENTE_REGISTRADO_OK.getCode(), mensaje, id));
	}
}
