package co.hospital.MicroRM.infrastructure.security;

import java.util.List;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Valida que el access token incluya la audience (API Identifier) configurada en Auth0.
 */
final class AudienceValidator implements OAuth2TokenValidator<Jwt> {

	private final String audience;

	AudienceValidator(String audience) {
		this.audience = audience;
	}

	@Override
	public OAuth2TokenValidatorResult validate(Jwt jwt) {
		List<String> audiences = jwt.getAudience();
		if (audiences != null && audiences.contains(audience)) {
			return OAuth2TokenValidatorResult.success();
		}
		return OAuth2TokenValidatorResult.failure(new OAuth2Error(
				"invalid_token",
				"El token no contiene la audience requerida: " + audience,
				null));
	}

}
