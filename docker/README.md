# PostgreSQL en Docker

## Arranque

Desde la carpeta `docker/`:

```powershell
copy env.example .env
docker compose up -d
```

Comprueba el estado:

```powershell
docker compose ps
docker compose logs -f postgres
```

## Conexión

| Parámetro | Valor por defecto |
|-----------|-------------------|
| Host | localhost |
| Puerto | 5432 |
| Base de datos | microrm |
| Usuario | microrm |

JDBC: `jdbc:postgresql://localhost:5432/microrm`

## Parar

```powershell
docker compose down
```

Borrar datos del volumen:

```powershell
docker compose down -v
```
