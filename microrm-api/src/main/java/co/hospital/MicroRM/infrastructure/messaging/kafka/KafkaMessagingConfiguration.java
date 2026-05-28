package co.hospital.MicroRM.infrastructure.messaging.kafka;

import co.hospital.MicroRM.infrastructure.messaging.MicrormKafkaProperties;
import co.hospital.MicroRM.infrastructure.messaging.OnKafkaEnabledCondition;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Conditional(OnKafkaEnabledCondition.class)
@EnableConfigurationProperties(MicrormKafkaProperties.class)
public class KafkaMessagingConfiguration {

	@Bean
	ProducerFactory<String, DomainEventEnvelope> domainEventProducerFactory(MicrormKafkaProperties properties) {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		config.put(ProducerConfig.ACKS_CONFIG, "all");
		config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	KafkaTemplate<String, DomainEventEnvelope> domainEventKafkaTemplate(
			ProducerFactory<String, DomainEventEnvelope> domainEventProducerFactory) {
		return new KafkaTemplate<>(domainEventProducerFactory);
	}

	@Bean
	ConsumerFactory<String, String> cdcConsumerFactory(MicrormKafkaProperties properties) {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		config.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getConsumerGroup() + "-cdc");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> cdcKafkaListenerContainerFactory(
			ConsumerFactory<String, String> cdcConsumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(cdcConsumerFactory);
		return factory;
	}

	@Bean
	DomainEventKafkaPublisher domainEventKafkaPublisher(
			KafkaTemplate<String, DomainEventEnvelope> kafkaTemplate,
			MicrormKafkaProperties properties) {
		return new DomainEventKafkaPublisher(kafkaTemplate, properties);
	}

	@Bean
	DebeziumCdcAuditListener debeziumCdcAuditListener() {
		return new DebeziumCdcAuditListener();
	}

}
