# CI/CD en GitHub (MicroRM)

## Workflow `CI` (`.github/workflows/ci.yml`)

Se ejecuta en **push** a `main`, `cache`, `CDC`, `WAF`, `API-getway`, `Aut0`, `CI/CD` y en **pull request** hacia `main`.

| Job | Qué hace |
|-----|----------|
| **Maven verify** | `mvn -B -ntp verify` en la raíz del repo (compila y ejecuta tests de `microrm-api` y `microrm-gateway`). |
| **Docker Compose syntax** | `docker compose config` sobre `docker/compose.yaml` (valida YAML y referencias). |

## Requisitos

- **JDK 26** (Temurin), alineado con `pom.xml` (`java.version`).

Si GitHub aún no ofrece Temurin 26 en el runner, cambia en el workflow `java-version` a la última LTS disponible (p. ej. `25`) y ajusta el `pom.xml` solo si el proyecto lo permite.

## Dependabot

`.github/dependabot.yml` abre PRs semanales para dependencias Maven y mensuales para acciones de GitHub.

## Próximos pasos (opcional)

- Publicar imágenes Docker en GHCR con un job condicionado a `main`.
- Matriz de pruebas con servicios (Postgres) para tests de integración.
