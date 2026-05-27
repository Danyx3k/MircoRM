package co.hospital.MicroRM.infrastructure.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI microRmOpenAPI() {
		final String bearerScheme = "bearer-jwt";
		return new OpenAPI()
				.info(new Info()
						.title("MicroRM API")
						.version("v1")
						.description(
								"API REST del laboratorio (pacientes y muestras). Use POST para crear, GET para consultar y PUT para actualizar. "
										+ "Con Auth0 activo (AUTH_ENABLED=true), envíe Authorization: Bearer &lt;access_token&gt;.")
						.contact(new Contact().name("Hospital — MicroRM")))
				.components(new Components().addSecuritySchemes(bearerScheme, new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")
						.description("Access token de Auth0 con la audience de la API")))
				.addSecurityItem(new SecurityRequirement().addList(bearerScheme));
	}
}
