package co.hospital.MicroRM.infrastructure.messaging.kafka;

import java.time.Instant;
import java.util.UUID;

public record DomainEventEnvelope(
		UUID eventId,
		String type,
		Instant occurredAt,
		Object payload) {
}
