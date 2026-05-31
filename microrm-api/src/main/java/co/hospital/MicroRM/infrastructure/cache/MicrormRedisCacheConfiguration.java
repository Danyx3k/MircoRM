package co.hospital.MicroRM.infrastructure.cache;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.StringUtils;

/**
 * Conexión Redis (salud del actuator y uso futuro). La caché HTTP de catálogos con Spring Cache
 * se desactivó: la deserialización a {@code List} de records provocaba 500 intermitentes.
 */
@Configuration
@Conditional(OnRedisEnabledCondition.class)
@EnableConfigurationProperties(MicrormCacheProperties.class)
public class MicrormRedisCacheConfiguration {

	@Bean
	LettuceConnectionFactory redisConnectionFactory(MicrormCacheProperties properties) {
		RedisStandaloneConfiguration standalone = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
		if (StringUtils.hasText(properties.getPassword())) {
			standalone.setPassword(RedisPassword.of(properties.getPassword()));
		}
		return new LettuceConnectionFactory(standalone);
	}

}
