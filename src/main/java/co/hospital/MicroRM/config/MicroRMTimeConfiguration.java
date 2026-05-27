package co.hospital.MicroRM.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableConfigurationProperties(MicroRMTimeProperties.class)
public class MicroRMTimeConfiguration {

	@Bean
	public ZoneId microRmBusinessZoneId(MicroRMTimeProperties properties) {
		return ZoneId.of(properties.businessTimezone());
	}

	@Bean
	public Clock microRmClock(ZoneId microRmBusinessZoneId) {
		return Clock.system(microRmBusinessZoneId);
	}
}
