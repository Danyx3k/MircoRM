package co.hospital.MicroRM.infrastructure.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(MicroRmAuthProperties.class)
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(
			HttpSecurity http,
			MicroRmAuthProperties auth,
			ObjectProvider<ColaboradorAuthFilter> colaboradorAuthFilter) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(Customizer.withDefaults());

		if (auth.isJwtEnabled()) {
			http.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
			colaboradorAuthFilter.ifAvailable(
					filter -> http.addFilterAfter(filter, BearerTokenAuthenticationFilter.class));
			http.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/actuator/health", "/actuator/health/**").permitAll()
					.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
					.requestMatchers("/api/**").authenticated()
					.anyRequest().denyAll());
		} else {
			http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
		}

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource(
			@org.springframework.beans.factory.annotation.Value("${app.microrm.cors.allowed-origin-patterns:http://localhost:*,http://127.0.0.1:*}") String allowedOriginPatterns) {
		CorsConfiguration config = new CorsConfiguration();
		List<String> patterns = Arrays.stream(allowedOriginPatterns.split(","))
				.map(String::trim)
				.filter(StringUtils::hasText)
				.toList();
		config.setAllowedOriginPatterns(patterns);
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);
		config.setExposedHeaders(List.of("Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
