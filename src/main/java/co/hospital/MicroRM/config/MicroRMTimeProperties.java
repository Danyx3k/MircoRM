package co.hospital.MicroRM.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Zona horaria para el "día de negocio" del consecutivo (reinicio a medianoche local de esa zona).
 */
@ConfigurationProperties("app.microrm")
public record MicroRMTimeProperties(String businessTimezone) {

	public MicroRMTimeProperties {
		if (businessTimezone == null || businessTimezone.isBlank()) {
			businessTimezone = "America/Bogota";
		}
	}
}
