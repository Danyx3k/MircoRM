# Kafka y CDC (Debezium)

## Componentes

| Servicio | Puerto host | Rol |
|----------|-------------|-----|
| `kafka` | 9092 | Broker (KRaft) |
| `debezium-connect` | 8083 | Kafka Connect + conector PostgreSQL |
| `debezium-setup` | — | Registra el conector al arrancar el stack |

## Tópicos

- **CDC (Debezium):** `microrm.microlab.<tabla>` (p. ej. `microrm.microlab.muestra`)
- **Eventos de dominio (API):** `microrm.domain.events`

## Postgres y WAL

El servicio `postgres` arranca con `wal_level=logical`. Si el volumen ya existía sin CDC, recree los datos:

```powershell
docker compose down -v
docker compose up -d --build
```

## Comprobar CDC

```powershell
docker compose logs -f debezium-setup
docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```
