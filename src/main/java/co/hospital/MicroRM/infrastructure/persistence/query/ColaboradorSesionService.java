package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.ColaboradorSesionResponse;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.ColaboradorJPAEntity;
import co.hospital.MicroRM.infrastructure.security.JwtEmailResolver;
import co.hospital.MicroRM.infrastructure.security.MicroRmAuthProperties;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorSesionService {

	private final ColaboradorJpaRepository colaboradorJpaRepository;
	private final MicroRmAuthProperties authProperties;

	public ColaboradorSesionService(
			ColaboradorJpaRepository colaboradorJpaRepository,
			MicroRmAuthProperties authProperties) {
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.authProperties = authProperties;
	}

	@Transactional(readOnly = true)
	public ColaboradorSesionResponse obtenerSesionActual(Jwt jwt) {
		if (!authProperties.isJwtEnabled()) {
			return toResponse(resolveColaboradorDesarrollo());
		}
		String email = JwtEmailResolver.resolveEmail(jwt);
		if (email == null) {
			throw MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO);
		}
		return colaboradorJpaRepository.findByCorreoElectronicoIgnoreCaseAndActivoTrue(email)
				.map(this::toResponse)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO));
	}

	private ColaboradorJPAEntity resolveColaboradorDesarrollo() {
		return colaboradorJpaRepository.findFirstByActivoTrueOrderByFechaCreacionAsc()
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO));
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
