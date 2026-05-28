package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.ColaboradorSesionResponse;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.ColaboradorJPAEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorSesionService {

	private final ColaboradorContextService colaboradorContextService;

	public ColaboradorSesionService(ColaboradorContextService colaboradorContextService) {
		this.colaboradorContextService = colaboradorContextService;
	}

	@Transactional(readOnly = true)
	public ColaboradorSesionResponse obtenerSesionActual(Jwt jwt) {
		return toResponse(colaboradorContextService.resolveColaboradorSesion(jwt));
	}

	private ColaboradorSesionResponse toResponse(ColaboradorJPAEntity entity) {
		String nombre = entity.getNombre() != null ? entity.getNombre().trim() : "";
		String apellido = entity.getApellido() != null ? entity.getApellido().trim() : "";
		String nombreCompleto = (nombre + " " + apellido).trim();
		return new ColaboradorSesionResponse(
				entity.getIdColaborador(),
				nombre,
				apellido,
				nombreCompleto,
				entity.getCorreoElectronico());
	}

}
