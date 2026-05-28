# Redis y caché en MicroRM

## Resumen

- **Redis 7** en Docker guarda en memoria las respuestas de **catálogos** (tipos de documento, sexos, EPS, sitios anatómicos, tipos de muestra).
- La API usa **Spring Cache** con `RedisCacheManager` y TTL configurable.
- Con `REDIS_ENABLED=false` no se conecta a Redis; `@Cacheable` no aplica (misma idea que Kafka con `KAFKA_ENABLED`).

## Arranque (Docker)

Desde `docker/`:

```powershell
copy env.example .env
docker compose up -d --build
```

La API espera a que `redis` esté healthy antes de arrancar.

## Desarrollo local sin Redis

En `application.properties` o variables de entorno:

```properties
REDIS_ENABLED=false
```

## Cachés

| Caché | Endpoint |
|--------|----------|
| `catalogo:sitios` | `GET /api/v1/catalogos/sitios-anatomicos` |
| `catalogo:tipos-muestra` | `GET /api/v1/catalogos/tipos-muestra` |
| `catalogo:tipos-documento` | `GET /api/v1/catalogos/tipos-documento` |
| `catalogo:sexos` | `GET /api/v1/catalogos/sexos` |
| `catalogo:eps` | `GET /api/v1/catalogos/eps` |

Claves Redis: prefijo `microrm:` + nombre del caché.

## Variables

| Variable | Descripción |
|----------|-------------|
| `REDIS_ENABLED` | Activa caché Redis en la API |
| `REDIS_HOST` | Host de Redis |
| `REDIS_PORT` | Puerto (6379) |
| `REDIS_CACHE_TTL_SECCONDS` | TTL en segundos (por defecto 3600) |
