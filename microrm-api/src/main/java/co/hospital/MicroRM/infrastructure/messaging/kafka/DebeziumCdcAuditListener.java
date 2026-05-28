package co.hospital.MicroRM.infrastructure.messaging.kafka;

import co.hospital.MicroRM.infrastructure.messaging.MicrormKafkaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
public class DebeziumCdcAuditListener {

	private static final Logger log = LoggerFactory.getLogger(DebeziumCdcAuditListener.class);

	@KafkaListener(
			topics = "${app.microrm.kafka.cdc-topic-paciente:microrm.microlab.paciente}",
			containerFactory = "cdcKafkaListenerContainerFactory")
	public void onPacienteChange(String payload) {
		log.debug("CDC paciente: {}", truncate(payload));
	}

	@KafkaListener(
			topics = "${app.microrm.kafka.cdc-topic-muestra:microrm.microlab.muestra}",
			containerFactory = "cdcKafkaListenerContainerFactory")
	public void onMuestraChange(String payload) {
		log.debug("CDC muestra: {}", truncate(payload));
	}

	@KafkaListener(
			topics = "${app.microrm.kafka.cdc-topic-muestra-paciente:microrm.microlab.muestra_paciente}",
			containerFactory = "cdcKafkaListenerContainerFactory")
	public void onMuestraPacienteChange(String payload) {
		log.debug("CDC muestra_paciente: {}", truncate(payload));
	}

	private static String truncate(String payload) {
		if (payload == null) {
			return "";
		}
		return payload.length() > 500 ? payload.substring(0, 500) + "…" : payload;
	}

}
