package co.hospital.MicroRM.infrastructure.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.microrm.kafka")
public class MicrormKafkaProperties {

	private boolean enabled = false;

	private String bootstrapServers = "localhost:9092";

	private String domainEventsTopic = "microrm.domain.events";

	/** Prefijo de tópicos Debezium (topic.prefix.schema.table). */
	private String cdcTopicPrefix = "microrm.microlab";

	private String consumerGroup = "microrm-api";

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getDomainEventsTopic() {
		return domainEventsTopic;
	}

	public void setDomainEventsTopic(String domainEventsTopic) {
		this.domainEventsTopic = domainEventsTopic;
	}

	public String getCdcTopicPrefix() {
		return cdcTopicPrefix;
	}

	public void setCdcTopicPrefix(String cdcTopicPrefix) {
		this.cdcTopicPrefix = cdcTopicPrefix;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

}
