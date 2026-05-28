package co.hospital.MicroRM.infrastructure.persistence.controler.sesion;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.ColaboradorSesionResponse;
import co.hospital.MicroRM.infrastructure.persistence.query.ColaboradorSesionService;
import co.hospital.MicroRM.infrastructure.security.MicroRmAuthProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Sesión", description = "Colaborador autenticado (vinculado al correo Auth0)")
@RestController
@RequestMapping("/api/v1/sesion")
public class SesionApiController {

	private final ColaboradorSesionService colaboradorSesionService;
	private final MicroRmAuthProperties authProperties;

	public SesionApiController(
			ColaboradorSesionService colaboradorSesionService,
			MicroRmAuthProperties authProperties) {
		this.colaboradorSesionService = colaboradorSesionService;
		this.authProperties = authProperties;
	}

	@Operation(summary = "Colaborador de la sesión actual")
	@GetMapping("/colaborador")
	public ResponseEntity<ColaboradorSesionResponse> colaboradorActual(
			@AuthenticationPrincipal Jwt jwt) {
		if (authProperties.isJwtEnabled() && jwt == null) {
			return ResponseEntity.status(401).build();
		}
		return ResponseEntity.ok(colaboradorSesionService.obtenerSesionActual(jwt));
	}

}
