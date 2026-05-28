# MicroRM en Docker



Stack local: **PostgreSQL**, **API** (`microrm-api`), **API Gateway** (`microrm-gateway`) y **WAF** (`microrm-waf`, ModSecurity + OWASP CRS).



## Arranque



Desde la carpeta `docker/`:



```powershell

copy env.example .env

docker compose up -d --build

```



Comprueba el estado:



```powershell

docker compose ps

docker compose logs -f microrm-gateway

```



## URLs



| Servicio | URL en el host |

|----------|----------------|

| **WAF** (entrada HTTP) | http://localhost:8080 |

| API (depuración directa, sin WAF) | http://localhost:8081 |

| Swagger UI | http://localhost:8080/swagger-ui/index.html |

| PostgreSQL | localhost:5432 |



Flujo: `Cliente → microrm-waf → microrm-gateway → microrm-api`. Ver [waf/README.md](waf/README.md).

Kafka y CDC (Debezium): [kafka/README.md](kafka/README.md) · [documentos/kafka-cdc.md](../documentos/kafka-cdc.md).

Redis (caché de catálogos): [redis/README.md](redis/README.md) · [documentos/redis-cache.md](../documentos/redis-cache.md).

### El front no ve el back (502 en `/actuator/health`)

Si el gateway se recreó y el WAF sigue con IP antigua, reinicie solo el WAF:

```powershell
docker compose restart microrm-waf
```

El compose usa `RESOLVERS=127.0.0.11` para re-resolver `microrm-gateway` cada pocos segundos. Tras `docker compose up -d --build`, si cambió el gateway, conviene `docker compose up -d --force-recreate microrm-waf`.

## Auth0 (FrontMicroRm)

El API en Docker arranca con JWT (`AUTH_ENABLED=true` por defecto en `env.example`). Debe coincidir con `VITE_AUTH0_AUDIENCE` del front (`https://microrm-api/`).

Tras cambios de seguridad en el API, reconstruya la imagen:

```powershell
docker compose up -d --build microrm-api
```

El correo con el que inicia sesión en Auth0 debe existir en `microlab.colaborador.correo_electronico` con `activo = true`.



## Conexión JDBC (desde el host)



| Parámetro | Valor por defecto |

|-----------|-------------------|

| Host | localhost |

| Puerto | 5432 |

| Base de datos | microrm |

| Usuario | microrm |



`jdbc:postgresql://localhost:5432/microrm`



## Parar



```powershell

docker compose down

```



Borrar datos del volumen:



```powershell

docker compose down -v

```



## Solo base de datos



Si prefieres levantar únicamente Postgres y ejecutar API/gateway con Maven en local:



```powershell

docker compose up -d postgres

```



Variables de BD en el host: `DB_HOST=localhost`, mismos `DB_*` que en `env.example`.


