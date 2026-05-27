package co.hospital.MicroRM.infrastructure.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

public final class JwtEmailResolver {

	private JwtEmailResolver() {
	}

	public static String resolveEmail(Jwt jwt) {
		if (jwt == null) {
			return null;
		}
		String email = jwt.getClaimAsString("email");
		if (StringUtils.hasText(email)) {
			return email.trim();
		}
		String preferred = jwt.getClaimAsString("preferred_username");
		if (StringUtils.hasText(preferred) && preferred.contains("@")) {
			return preferred.trim();
		}
		return null;
	}

}
