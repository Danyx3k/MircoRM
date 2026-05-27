package co.hospital.MicroRM.infrastructure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = "app.microrm.auth")
public class MicroRmAuthProperties {

	private boolean enabled = false;

	/** Issuer Auth0, p. ej. https://tu-tenant.us.auth0.com/ */
	private String issuerUri = "";

	/** API Identifier (audience) definido en Auth0 → APIs */
	private String audience = "";

	/** JWT activo solo si está habilitado y hay issuer + audience (evita arranque roto). */
	public boolean isJwtEnabled() {
		return enabled && StringUtils.hasText(issuerUri) && StringUtils.hasText(audience);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getIssuerUri() {
		return issuerUri;
	}

	public void setIssuerUri(String issuerUri) {
		this.issuerUri = issuerUri;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

}
