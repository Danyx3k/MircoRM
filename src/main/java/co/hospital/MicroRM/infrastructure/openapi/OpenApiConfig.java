package co.hospital.MicroRM.infrastructure.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI microRmOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("MicroRM API")
						.version("v1")
						.description("API REST del laboratorio (pacientes y muestras). Use POST para crear, GET para consultar y PUT para actualizar.")
						.contact(new Contact().name("Hospital — MicroRM")));
	}
}
