# Redis en Docker (MicroRM)

Servicio **redis:7-alpine** para caché de catálogos en la API (`/api/v1/catalogos/*`).

## Variables (`.env`)

| Variable | Descripción |
|----------|-------------|
| `REDIS_ENABLED` | Activa Spring Cache + Redis en la API |
| `REDIS_HOST` | `redis` dentro de Compose, `localhost` en Maven local |
| `REDIS_PORT` | Puerto publicado (por defecto 6379) |
| `REDIS_CACHE_TTL_SECCONDS` | TTL de entradas de catálogo (por defecto 3600) |

## Comprobar

```powershell
docker compose exec redis redis-cli ping
# PONG

docker compose exec redis redis-cli KEYS "microrm:*"
```

Tras varias peticiones a catálogos, deberían aparecer claves con prefijo `microrm:catalogo:`.
