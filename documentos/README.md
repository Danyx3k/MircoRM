# Documentación MicroRM

## Casos de uso

| UC | Método | Ruta |
|----|--------|------|
| Registrar paciente | POST | /api/v1/pacientes |
| Registrar muestra | POST | /api/v1/muestras |

DTOs: `RegisterPacienteRequest` (identificación alfanumérica máx. 15, `nombre` y `apellido` máx. 20, `fechaNacimiento` en JSON como **ISO-8601 fecha** `yyyy-MM-dd` → tipo PostgreSQL `DATE`), `RegisterMuestraRequest` en `infrastructure.persistence.mapper.dto`. El registro de muestra exige `idColaboradorRegistra`, `idColaboradorProcesa` (UUID de colaboradores activos) y `esContaminada`. Observaciones separadas: **`observacionesClinicas`** y **`observacionesLaboratorio`** (opcionales); **`fechaHoraProcesamiento`** opcional. **No** se envía `numeroLaboratorio`: se genera con consecutivo diario global (ver [consecutivo-muestra.md](consecutivo-muestra.md)). Flyway `V3` crea `roles_colaborador` / `colaborador` y semilla un colaborador de desarrollo (`d0000000-0000-4000-a000-000000000001`, usuario `sistema.dev`). Flyway `V4` crea la tabla `consecutivo_muestra_diario`.

## Si falla el arranque con la base de datos

- Mensaje **`Schema validation: missing table [colaborador]`** (u otras tablas) → migraciones Flyway incompletas o tablas en otro esquema; la app usa **`microlab`**. Ver [troubleshooting-db.md](troubleshooting-db.md).
- Mensaje **`Schema validation: missing column [nombre_completo] in table [colaborador]`** (versiones antiguas) → el modelo actual usa **`nombre` + `apellido`**, alineado con `microlab.colaborador`. Migración de legado: Flyway **V7**. Si el fallo persiste, compara el DDL con `ColaboradorJPAEntity`.

## Infraestructura

- PostgreSQL + Flyway: `docker/` y `db/migration`.
- MapStruct: [mapstruct.md](mapstruct.md).
- Seguridad: `SecurityConfig` (dev: `/api/**` permitido sin CSRF).
