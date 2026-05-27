# Consecutivo diario del número de laboratorio (muestras)

## Regla de negocio

- Un **solo** `numeroLaboratorio` por muestra, **único en todo el sistema por día** (no depende del paciente: dos pacientes distintos no comparten el mismo consecutivo el mismo día).
- Formato: **AAMMDD** (año 2 dígitos, mes 2, día 2) + **3 dígitos** de `000` a `999` → máximo **1000** muestras por día civil.
- El contador se basa en el **día de negocio** según `app.microrm.business-timezone` (medianoche local de esa zona = reinicio a `…000`).
- El cliente **no envía** el número: lo asigna el servidor al registrar la muestra.

## Concurrencia y “reactivo”

El servicio usa **Spring WebMVC + JPA** (bloqueante). La exclusión mutua entre muchos colaboradores registrando a la vez se resuelve en **PostgreSQL** con una fila por fecha en `consecutivo_muestra_diario` y una sentencia atómica `INSERT … ON CONFLICT DO UPDATE … RETURNING`, de modo que las peticiones concurrentes se serializan en esa fila sin duplicar índices.

Una migración completa a **programación reactiva** (WebFlux + R2DBC) implicaría otro stack de persistencia; la **misma** estrategia SQL atómica seguiría siendo válida en R2DBC (`DatabaseClient` / transacción reactiva).

## Perfil `test`

Con `spring.profiles.active=test` se usa `TestNumeroLaboratorioSequenceAdapter` (memoria, sincronizado por JVM) para no depender de la tabla en H2.

## Errores

- `MUESTRA_CONSECUTIVO_DIARIO_AGOTADO` (`MRM-2018`): ya se asignaron 1000 números ese día.
