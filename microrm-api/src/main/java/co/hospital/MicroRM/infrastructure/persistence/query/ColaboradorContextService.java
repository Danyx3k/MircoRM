package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.entity.ColaboradorJPAEntity;
import co.hospital.MicroRM.infrastructure.security.ColaboradorEmailResolver;
import co.hospital.MicroRM.infrastructure.security.MicroRmAuthProperties;
import org.springframework.beans.factory.ObjectProvider;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Resuelve el colaborador activo vinculado al correo del JWT Auth0.
 */
@Service
public class ColaboradorContextService {

	private static final UUID COLABORADOR_SISTEMA = UUID.fromString("b0000000-0000-4000-b000-000000000001");

	private final ColaboradorJpaRepository colaboradorJpaRepository;
	private final MicroRmAuthProperties authProperties;
	private final ObjectProvider<ColaboradorEmailResolver> colaboradorEmailResolver;

	public ColaboradorContextService(
			ColaboradorJpaRepository colaboradorJpaRepository,
			MicroRmAuthProperties authProperties,
			ObjectProvider<ColaboradorEmailResolver> colaboradorEmailResolver) {
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.authProperties = authProperties;
		this.colaboradorEmailResolver = colaboradorEmailResolver;
	}

	@Transactional(readOnly = true)
	public ColaboradorJPAEntity resolveColaboradorSesion(Jwt jwt) {
		Jwt token = jwt != null ? jwt : currentJwt().orElse(null);
		if (token != null) {
			return resolveByJwtOrThrow(token);
		}
		if (authProperties.isJwtEnabled()) {
			throw MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO);
		}
		return resolveColaboradorDesarrollo();
	}

	@Transactional(readOnly = true)
	public UUID resolveColaboradorCapturaId() {
		return resolveColaboradorSesion(currentJwt().orElse(null)).getIdColaborador();
	}

	private ColaboradorJPAEntity resolveByJwtOrThrow(Jwt jwt) {
		ColaboradorEmailResolver resolver = colaboradorEmailResolver.getIfAvailable();
		String email = resolver != null ? resolver.resolve(jwt) : null;
		if (!StringUtils.hasText(email)) {
			throw MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO);
		}
		return colaboradorJpaRepository.findByCorreoElectronicoIgnoreCaseAndActivoTrue(email.trim())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO));
	}

	private ColaboradorJPAEntity resolveColaboradorDesarrollo() {
		if (colaboradorJpaRepository.existsByIdColaboradorAndActivoTrue(COLABORADOR_SISTEMA)) {
			return colaboradorJpaRepository.findById(COLABORADOR_SISTEMA)
					.orElseThrow(() -> MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO));
		}
		return colaboradorJpaRepository.findFirstByActivoTrueOrderByFechaCreacionAsc()
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COLABORADOR_NO_AUTORIZADO));
	}

	private Optional<Jwt> currentJwt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof JwtAuthenticationToken jwtAuth) {
			return Optional.of(jwtAuth.getToken());
		}
		return Optional.empty();
	}

}
