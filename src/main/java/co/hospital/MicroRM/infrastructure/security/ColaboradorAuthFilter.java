package co.hospital.MicroRM.infrastructure.security;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
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

	private final MicroRmAuthProperties authProperties;
	private final ColaboradorJpaRepository colaboradorJpaRepository;

	public ColaboradorAuthFilter(
			MicroRmAuthProperties authProperties,
			ColaboradorJpaRepository colaboradorJpaRepository) {
		this.authProperties = authProperties;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		if (!authProperties.isJwtEnabled()) {
			return true;
		}
		String path = request.getRequestURI();
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
		String email = JwtEmailResolver.resolveEmail(jwt);
		if (!StringUtils.hasText(email)
				|| colaboradorJpaRepository.findByCorreoElectronicoIgnoreCaseAndActivoTrue(email).isEmpty()) {
			writeForbidden(response);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private void writeForbidden(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		String codigo = MessagesEnum.COLABORADOR_NO_AUTORIZADO.getCode();
		String mensaje = jsonEscape(MessagesEnum.COLABORADOR_NO_AUTORIZADO.getDefaultMessage());
		response.getWriter().write("{\"codigo\":\"" + codigo + "\",\"mensaje\":\"" + mensaje + "\"}");
	}

	private static String jsonEscape(String value) {
		if (value == null) {
			return "";
		}
		return value.replace("\\", "\\\\").replace("\"", "\\\"");
	}

}
