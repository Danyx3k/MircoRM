# Redis en MicroRM

## Resumen

- **Redis 7** en Docker: la API abre conexión (`LettuceConnectionFactory`) cuando `REDIS_ENABLED=true` (salud en actuator y base para uso futuro).
- **No** se usa Spring Cache sobre catálogos: serializar `List` de records (`CatalogoItemResponse`) en Redis provocaba **500** al navegar entre pantallas.
- Con `REDIS_ENABLED=false` no se conecta a Redis (misma idea que Kafka con `KAFKA_ENABLED`).

## Arranque (Docker)

Desde `docker/`:

```powershell
copy env.example .env
docker compose up -d --build
```

La API espera a que `redis` esté healthy antes de arrancar.

## Desarrollo local sin Redis

```properties
REDIS_ENABLED=false
```

## Variables

| Variable | Descripción |
|----------|-------------|
| `REDIS_ENABLED` | Activa conexión Redis en la API |
| `REDIS_HOST` | Host de Redis |
| `REDIS_PORT` | Puerto (6379) |
