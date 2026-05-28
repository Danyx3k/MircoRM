package co.hospital.MicroRM.infrastructure.security;

import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@ConditionalOnBean(JwtDecoder.class)
public class ColaboradorAuthFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(ColaboradorAuthFilter.class);

	private final MicroRmAuthProperties authProperties;
	private final ColaboradorJpaRepository colaboradorJpaRepository;
	private final ColaboradorEmailResolver colaboradorEmailResolver;

	public ColaboradorAuthFilter(
			MicroRmAuthProperties authProperties,
			ColaboradorJpaRepository colaboradorJpaRepository,
			ColaboradorEmailResolver colaboradorEmailResolver) {
		this.authProperties = authProperties;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
		this.colaboradorEmailResolver = colaboradorEmailResolver;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		if (!authProperties.isJwtEnabled()) {
			return true;
		}
		String path = request.getRequestURI();
		if ("GET".equalsIgnoreCase(request.getMethod()) && path.startsWith("/api/v1/catalogos/")) {
			return true;
		}
		return !path.startsWith("/api/");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
			filterChain.doFilter(request, response);
			return;
		}

		Jwt jwt = jwtAuth.getToken();
		String email = colaboradorEmailResolver.resolve(jwt);
		if (!StringUtils.hasText(email)) {
			log.warn("Acceso denegado: el token no incluye correo (ruta {}). Añada scope email en Auth0 y compruebe /userinfo.",
					request.getRequestURI());
			SecurityProblemResponseWriter.writeColaboradorNoAutorizado(response);
			return;
		}
		if (colaboradorJpaRepository.findByCorreoElectronicoIgnoreCaseAndActivoTrue(email).isEmpty()) {
			log.warn("Acceso denegado: correo {} sin colaborador activo en microlab (ruta {}).", email,
					request.getRequestURI());
			SecurityProblemResponseWriter.writeColaboradorNoAutorizado(response);
			return;
		}

		filterChain.doFilter(request, response);
	}

}
