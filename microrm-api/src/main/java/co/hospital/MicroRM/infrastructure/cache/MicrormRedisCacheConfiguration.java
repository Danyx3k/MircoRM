package co.hospital.MicroRM.infrastructure.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Configuration
@EnableCaching
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

	@Bean
	RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory, MicrormCacheProperties properties) {
		ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
		GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
		RedisSerializationContext.SerializationPair<Object> pair =
				RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer);

		RedisCacheConfiguration defaults = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(properties.getDefaultTtlSeconds()))
				.prefixCacheNameWith("microrm:")
				.serializeValuesWith(pair);

		return RedisCacheManager.builder(connectionFactory).cacheDefaults(defaults).build();
	}

}
