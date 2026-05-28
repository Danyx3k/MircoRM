package co.hospital.MicroRM.infrastructure.security;

import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/**
 * Obtiene el correo del usuario desde Auth0 /userinfo cuando el access token no lo incluye (comportamiento habitual).
 */
@Component
@Conditional(OnJwtAuthEnabledCondition.class)
class Auth0UserinfoClient {

	private static final Logger log = LoggerFactory.getLogger(Auth0UserinfoClient.class);

	private final RestClient restClient;

	Auth0UserinfoClient(MicroRmAuthProperties auth) {
		String issuer = auth.getIssuerUri().trim();
		if (issuer.endsWith("/")) {
			issuer = issuer.substring(0, issuer.length() - 1);
		}
		this.restClient = RestClient.builder().baseUrl(issuer).build();
	}

	Optional<String> fetchEmail(String accessToken) {
		if (!StringUtils.hasText(accessToken)) {
			return Optional.empty();
		}
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> body = restClient.get()
					.uri("/userinfo")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
					.retrieve()
					.body(Map.class);
			if (body == null) {
				return Optional.empty();
			}
			Object email = body.get("email");
			if (email instanceof String s && StringUtils.hasText(s)) {
				return Optional.of(s.trim());
			}
			Object preferred = body.get("preferred_username");
			if (preferred instanceof String p && StringUtils.hasText(p) && p.contains("@")) {
				return Optional.of(p.trim());
			}
			return Optional.empty();
		} catch (RestClientException ex) {
			log.warn("No se pudo leer /userinfo de Auth0: {}", ex.getMessage());
			return Optional.empty();
		}
	}

}
