package co.hospital.MicroRM.infrastructure.messaging.kafka;

import co.hospital.MicroRM.infrastructure.messaging.MicrormKafkaProperties;
import co.hospital.MicroRM.infrastructure.messaging.domain.MuestraRegistradaEvent;
import co.hospital.MicroRM.infrastructure.messaging.domain.PacienteRegistradoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.util.UUID;

public class DomainEventKafkaPublisher {

	private static final Logger log = LoggerFactory.getLogger(DomainEventKafkaPublisher.class);

	private final KafkaTemplate<String, DomainEventEnvelope> kafkaTemplate;
	private final MicrormKafkaProperties properties;

	public DomainEventKafkaPublisher(
			KafkaTemplate<String, DomainEventEnvelope> kafkaTemplate,
			MicrormKafkaProperties properties) {
		this.kafkaTemplate = kafkaTemplate;
		this.properties = properties;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onPacienteRegistrado(PacienteRegistradoEvent event) {
		publish("paciente.registrado", event.idPaciente().toString(), event);
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onMuestraRegistrada(MuestraRegistradaEvent event) {
		publish("muestra.registrada", event.idMuestra().toString(), event);
	}

	private void publish(String type, String key, Object payload) {
		DomainEventEnvelope envelope = new DomainEventEnvelope(UUID.randomUUID(), type, Instant.now(), payload);
		try {
			kafkaTemplate.send(properties.getDomainEventsTopic(), key, envelope);
			log.debug("Evento Kafka publicado: type={} key={}", type, key);
		} catch (Exception ex) {
			log.error("No se pudo publicar evento Kafka type={} key={}: {}", type, key, ex.getMessage());
		}
	}

}
