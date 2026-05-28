package co.hospital.MicroRM.infrastructure.security;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * JWT solo si auth está habilitado y hay issuer + audience (evita caída al arrancar mal configurado).
 */
class OnJwtAuthEnabledCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String enabled = context.getEnvironment().getProperty("app.microrm.auth.enabled", "false");
		if (!"true".equalsIgnoreCase(enabled)) {
			return false;
		}
		String issuer = context.getEnvironment().getProperty("app.microrm.auth.issuer-uri", "");
		String audience = context.getEnvironment().getProperty("app.microrm.auth.audience", "");
		return StringUtils.hasText(issuer) && StringUtils.hasText(audience);
	}

}
