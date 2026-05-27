package co.hospital.MicroRM.infrastructure.persistence.controler.muestra;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessageCatalog;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.RegisterNewMuestraInputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
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
@RequestMapping("/api/v1/muestras")
public class RegisterNewMuestraController {

	private final RegisterNewMuestraInputPort inputPort;
	private final MessageCatalog messageCatalog;

	public RegisterNewMuestraController(RegisterNewMuestraInputPort inputPort, MessageCatalog messageCatalog) {
		this.inputPort = inputPort;
		this.messageCatalog = messageCatalog;
	}

	@PostMapping
	public ResponseEntity<RegisterNewMuestraResponse> register(@Valid @RequestBody RegisterMuestraRequest request) {
		UUID id = inputPort.execute(request);
		String mensaje = messageCatalog.getUserMessage(MessagesEnum.MUESTRA_REGISTRADA_OK, LocaleContextHolder.getLocale());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new RegisterNewMuestraResponse(MessagesEnum.MUESTRA_REGISTRADA_OK.getCode(), mensaje, id));
	}
}
