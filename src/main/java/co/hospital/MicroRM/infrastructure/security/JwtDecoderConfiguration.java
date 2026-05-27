package co.hospital.MicroRM.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@Conditional(OnJwtAuthEnabledCondition.class)
class JwtDecoderConfiguration {

	@Bean
	JwtDecoder jwtDecoder(MicroRmAuthProperties auth) {
		String issuer = auth.getIssuerUri().trim();
		try {
			NimbusJwtDecoder decoder = NimbusJwtDecoder.withIssuerLocation(issuer).build();
			OAuth2TokenValidator<Jwt> defaultWithIssuer = JwtValidators.createDefaultWithIssuer(issuer);
			OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(auth.getAudience().trim());
			decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(defaultWithIssuer, audienceValidator));
			return decoder;
		} catch (RuntimeException ex) {
			throw new IllegalStateException(
					"No se pudo inicializar JWT con issuer " + issuer
							+ ". Compruebe red/Auth0 o use AUTH_ENABLED=false.",
					ex);
		}
	}

}
