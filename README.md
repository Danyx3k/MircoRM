# MicroRM

Backend Spring Boot multi-módulo: **API** (`microrm-api`) y **API Gateway** (`microrm-gateway`). Documentación: [documentos/README.md](documentos/README.md).

## Arranque local

Terminal 1 — API (puerto **8081**):

```bash
mvn -pl microrm-api spring-boot:run
```

Terminal 2 — Gateway (puerto **8080**, enruta al API):

```bash
mvn -pl microrm-gateway spring-boot:run
```

Punto de entrada HTTP: `http://localhost:8080` (p. ej. `/api/v1/...`, Swagger en `/swagger-ui/index.html`).

Variables opcionales: `API_PORT`, `GATEWAY_PORT`, `MICRORM_API_URI` (URI del API visto desde el gateway).

## Docker (Postgres + API + Gateway)

```bash
cd docker
copy env.example .env    # Windows
docker compose up -d --build
```

Detalle en [docker/README.md](docker/README.md).
