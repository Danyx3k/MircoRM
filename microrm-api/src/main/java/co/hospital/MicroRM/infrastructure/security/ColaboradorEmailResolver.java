package co.hospital.MicroRM.infrastructure.security;

import org.springframework.context.annotation.Conditional;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Correo del colaborador: primero claims del JWT; si no hay email, consulta Auth0 /userinfo.
 */
@Component
@Conditional(OnJwtAuthEnabledCondition.class)
public class ColaboradorEmailResolver {

	private final Auth0UserinfoClient userinfoClient;

	public ColaboradorEmailResolver(Auth0UserinfoClient userinfoClient) {
		this.userinfoClient = userinfoClient;
	}

	public String resolve(Jwt jwt) {
		if (jwt == null) {
			return null;
		}
		String email = JwtEmailResolver.resolveEmail(jwt);
		if (StringUtils.hasText(email)) {
			return email.trim();
		}
		return userinfoClient.fetchEmail(jwt.getTokenValue()).orElse(null);
	}

}
