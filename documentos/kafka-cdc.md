# Kafka y CDC en MicroRM

## Resumen

- **Apache Kafka** como bus de eventos.
- **Debezium** captura cambios en PostgreSQL (`microlab`) y los publica en tópicos `microrm.microlab.*`.
- La **API** publica eventos de dominio en `microrm.domain.events` al registrar paciente o muestra.

## Arranque (Docker)

Desde `docker/`:

```powershell
copy env.example .env
docker compose up -d --build
```

Servicios: `kafka`, `debezium-connect`, `debezium-setup`, `microrm-api` (con `KAFKA_ENABLED=true`).

## Tópicos

| Tópico | Origen |
|--------|--------|
| `microrm.microlab.paciente` | CDC Debezium |
| `microrm.microlab.muestra` | CDC Debezium |
| `microrm.microlab.muestra_paciente` | CDC Debezium |
| `microrm.domain.events` | API (eventos de negocio) |

## Variables

| Variable | Descripción |
|----------|-------------|
| `KAFKA_ENABLED` | Activa productor/consumidor en la API |
| `KAFKA_BOOTSTRAP_SERVERS` | `kafka:9092` en Docker, `localhost:9092` en local |
| `MODSEC_*` | WAF (ver `docker/waf/`) |

## Desarrollo local sin Docker Kafka

```properties
KAFKA_ENABLED=false
```

## Postgres y WAL

Si CDC no arranca, el volumen Postgres puede haberse creado sin `wal_level=logical`:

```powershell
docker compose down -v
docker compose up -d --build
```

Más detalle: [docker/kafka/README.md](../docker/kafka/README.md).
