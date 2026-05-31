# Redis en Docker (MicroRM)

Servicio **redis:7-alpine** para que la API mantenga conexión Redis (actuator y extensión futura).

## Variables (`.env`)

| Variable | Descripción |
|----------|-------------|
| `REDIS_ENABLED` | Activa conexión Redis en la API |
| `REDIS_HOST` | `redis` dentro de Compose, `localhost` en Maven local |
| `REDIS_PORT` | Puerto publicado (por defecto 6379) |

## Comprobar

```powershell
docker compose exec redis redis-cli ping
# PONG
```
