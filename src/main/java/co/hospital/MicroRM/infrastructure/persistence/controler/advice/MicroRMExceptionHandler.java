package co.hospital.MicroRM.infrastructure.persistence.controler.advice;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MicroRMExceptionHandler {

	@ExceptionHandler(MicroRMException.class)
	public ResponseEntity<Map<String, Object>> handleMicroRM(MicroRMException ex) {
		Map<String, Object> body = new HashMap<>();
		if (ex.hasCatalogMessageCode()) {
			body.put("codigo", ex.getMessageCode().getCode());
			body.put("mensaje", ex.getMessageCode().getDefaultMessage());
		} else {
			body.put("codigo", "MRM-0000");
			body.put("mensaje", ex.getUserMessage() != null ? ex.getUserMessage() : "Error");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
		String detalle = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining("; "));
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of(
				"codigo", "MRM-0002",
				"mensaje", "Errores de validación en la petición.",
				"detalle", detalle));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
				"codigo", "MRM-0003",
				"mensaje", "Cuerpo JSON inválido o incompleto."));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
				"codigo", "MRM-0004",
				"mensaje", "Violación de integridad de datos (clave única o foránea)."));
	}
}
