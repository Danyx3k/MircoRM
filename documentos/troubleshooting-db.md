# Errores de base de datos al arrancar

## Esquema `microlab` (no `public`)

Si las tablas están bajo el esquema **`microlab`** en PostgreSQL, la aplicación debe usar el mismo esquema en JPA y Flyway (ya configurado en `application.properties`):

- `spring.jpa.properties.hibernate.default_schema=microlab`
- `spring.flyway.schemas=microlab` y `spring.flyway.default-schema=microlab`

Flyway puede **crear** el esquema si no existe al arrancar. El historial de migraciones quedará en `microlab`.

Si antes migraste solo en `public`, tendrás que unificar (mover tablas o recrear la BD y dejar que Flyway ejecute todo en `microlab`).

## `missing column [...] in table [medios_cultivo]`

El catálogo hospitalario usa **`temperatura_incubacion`**, **`proveedor`**, **`activo`**, **`fecha_creacion`** y **`fecha_actualizacion`** (ver `MedioCultivoJPAEntity` y **V1**/**V2**/**V8**). Si tu tabla es antigua (p. ej. solo `temperatura_celsius`), deja que Flyway ejecute **V8** o alinea el DDL manualmente.

Si **cambiaste el script V1** ya aplicado, Flyway marcará checksum distinto: usa `flyway repair` o vuelve a crear la base en desarrollo.

## Tabla `muestras`: dos observaciones

El modelo hospitalario usa **`observaciones_clinicas`** y **`observaciones_laboratorio`** (TEXT), más **`fecha_hora_procesamiento`** y **`usuario_actualiza`**. Instalaciones con la columna única **`observaciones`** quedan migradas por Flyway **V9** (contenido previo se copia a `observaciones_laboratorio`).

---

El código JPA ya **no** usa `nombre_completo`: mapea **`nombre`** y **`apellido`** (y el resto de columnas de auditoría). Flyway **V7** convierte instalaciones que aún tengan solo `nombre_completo` (V3 antiguo) a `nombre`/`apellido`.

---

**Causas habituales**

1. **Flyway no ha aplicado las migraciones** en esa base (BD nueva sin arranque previo, o error silencioso en un intento anterior).
2. **`SPRING_FLYWAY_ENABLED=false`** (o equivalente en variables de entorno / perfil de ejecución del IDE).
3. **Base de datos equivocada** (`DB_NAME`, URL JDBC) distinta de la que crees.
4. **Estado incoherente:** existe `flyway_schema_history` apuntando a versiones que no corresponden a las tablas reales (restauración parcial, pruebas manuales, etc.).

**Qué hacer**

1. Confirma en los logs al arrancar líneas de Flyway del tipo: `Migrating schema ... to version 3 ...` hasta la **V5**. Si no aparecen, Flyway no está migrando esa URL.
2. En PostgreSQL, revisa tablas e historial (ajusta `microlab` si usas otro esquema):
   ```sql
   SELECT installed_rank, version, description, success
   FROM microlab.flyway_schema_history
   ORDER BY installed_rank;

   SELECT tablename FROM pg_tables WHERE schemaname = 'microlab' ORDER BY tablename;
   ```
3. **Entorno local limpio (Docker):** borra el volumen de datos y vuelve a levantar Postgres para que Flyway ejecute todo desde cero (según tu `docker/README.md`).
4. Si la BD debe conservarse, corrige el historial o ejecuta migraciones pendientes con la [CLI de Flyway](https://documentation.red-gate.com/flyway) o el plugin de Maven apuntando al mismo JDBC que la app.

**Nota:** `Maximum pool size: undefined/unknown` en los logs de Hikari es informativo en la configuración por defecto; **no** es la causa de este error.
