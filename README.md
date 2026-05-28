# MicroRM



Backend Spring Boot multi-módulo: **API** (`microrm-api`) y **API Gateway** (`microrm-gateway`). Documentación: [documentos/README.md](documentos/README.md).



## Arranque local



Terminal 1 — API (puerto **8081**):



Auth0 (JWT en `/api/**`): [documentos/auth0.md](documentos/auth0.md).



```bash

mvn -pl microrm-api spring-boot:run

```



Terminal 2 — Gateway (puerto **8080**, enruta al API):



```bash

mvn -pl microrm-gateway spring-boot:run

```



Punto de entrada HTTP: `http://localhost:8080` (p. ej. `/api/v1/...`, Swagger en `/swagger-ui/index.html`).



Variables opcionales: `API_PORT`, `GATEWAY_PORT`, `MICRORM_API_URI` (URI del API visto desde el gateway).



## Docker (Postgres + API + Gateway + WAF ModSecurity)



```bash

cd docker

copy env.example .env    # Windows

docker compose up -d --build

```



Detalle en [docker/README.md](docker/README.md).



### Si no arranca



1. **Puerto 8080 u 8081 ocupado**: cierre otra instancia de MicroRM, Docker o el IDE. En PowerShell: `Get-NetTCPConnection -LocalPort 8080 | Select OwningProcess` (o `8081`) y termine ese PID.

2. **Auth0 / JWT**: con `AUTH_ENABLED=true` hace falta red hacia Auth0 y `AUTH0_ISSUER_URI` + `AUTH0_AUDIENCE`. Para desarrollo use `AUTH_ENABLED=false` en `application-auth0.local.properties` (se importa solo).

3. **PostgreSQL**: debe estar en marcha en `localhost:5432`, base `microrm`, usuario `microrm` / contraseña `microrm_dev` (o sus variables `DB_*`).

