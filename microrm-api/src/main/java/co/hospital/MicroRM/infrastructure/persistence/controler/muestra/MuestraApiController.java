package co.hospital.MicroRM.infrastructure.persistence.controler.muestra;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessageCatalog;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.features.muestra.getmuestra.application.inputport.GetMuestraInputPort;
import co.hospital.MicroRM.features.muestra.registernewmuestra.application.inputport.RegisterNewMuestraInputPort;
import co.hospital.MicroRM.features.muestra.updatemuestra.application.inputport.UpdateMuestraInputPort;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.MuestraResponse;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.RegisterMuestraRequest;
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

import java.util.UUID;

@Tag(name = "Muestras", description = "Registro (POST), consulta por id (GET) y actualización (PUT). El número de laboratorio no se modifica por API.")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
@RestController
@RequestMapping("/api/v1/muestras")
public class MuestraApiController {

	private final RegisterNewMuestraInputPort registerNewMuestraInputPort;
	private final GetMuestraInputPort getMuestraInputPort;
	private final UpdateMuestraInputPort updateMuestraInputPort;
	private final MessageCatalog messageCatalog;

	public MuestraApiController(
			RegisterNewMuestraInputPort registerNewMuestraInputPort,
			GetMuestraInputPort getMuestraInputPort,
			UpdateMuestraInputPort updateMuestraInputPort,
			MessageCatalog messageCatalog) {
		this.registerNewMuestraInputPort = registerNewMuestraInputPort;
		this.getMuestraInputPort = getMuestraInputPort;
		this.updateMuestraInputPort = updateMuestraInputPort;
		this.messageCatalog = messageCatalog;
	}

	@Operation(summary = "Registrar muestra", description = "Crea una muestra; el número de laboratorio se genera en servidor.")
	@PostMapping
	public ResponseEntity<RegisterNewMuestraResponse> registrar(@Valid @RequestBody RegisterMuestraRequest request) {
		UUID id = registerNewMuestraInputPort.execute(request);
		String mensaje = messageCatalog.getUserMessage(MessagesEnum.MUESTRA_REGISTRADA_OK, LocaleContextHolder.getLocale());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new RegisterNewMuestraResponse(MessagesEnum.MUESTRA_REGISTRADA_OK.getCode(), mensaje, id));
	}

	@Operation(summary = "Obtener muestra por id")
	@GetMapping("/{id}")
	public ResponseEntity<MuestraResponse> obtener(@PathVariable UUID id) {
		return ResponseEntity.ok(MuestraResponse.from(getMuestraInputPort.execute(id)));
	}

	@Operation(summary = "Actualizar muestra", description = "Actualiza datos de la muestra. El número de laboratorio existente se conserva.")
	@PutMapping("/{id}")
	public ResponseEntity<MuestraResponse> actualizar(
			@PathVariable UUID id,
			@Valid @RequestBody RegisterMuestraRequest request) {
		return ResponseEntity.ok(MuestraResponse.from(updateMuestraInputPort.execute(id, request)));
	}
}
