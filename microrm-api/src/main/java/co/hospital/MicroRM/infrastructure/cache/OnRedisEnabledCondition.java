package co.hospital.MicroRM.infrastructure.cache;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class OnRedisEnabledCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		if (!"true".equalsIgnoreCase(context.getEnvironment().getProperty("app.microrm.cache.enabled", "false"))) {
			return false;
		}
		return StringUtils.hasText(context.getEnvironment().getProperty("app.microrm.cache.host", ""));
	}

}
