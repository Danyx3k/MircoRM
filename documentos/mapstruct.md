# MapStruct en MicroRM

## Configuración Maven

- Dependencia `org.mapstruct:mapstruct` (versión en `pom.xml`, propiedad `mapstruct.version`).
- Procesador `mapstruct-processor` en `maven-compiler-plugin` / `annotationProcessorPaths`.
- Argumentos del compilador:
  - `-Amapstruct.defaultComponentModel=spring` — los mappers son beans `@Component` inyectables.
  - `-Amapstruct.unmappedTargetPolicy=IGNORE` — ignora propiedades destino no mapeadas (útil en entidades JPA con asociaciones).

## Mappers

| Interfaz | Uso |
|----------|-----|
| `PacienteJpaEntityMapper` | `PacienteEntity` (dominio) ↔ `PacienteJPAEntity` (mapea `id` ↔ `idPaciente`). |
| `RegisterPacienteApiMapper` | `RegisterPacienteRequest` (DTO record) ↔ `PacienteEntity`. |
| `MuestraEntityMapper` | `MuestraEntity` ↔ `MuestraJPAEntity` (IDs planos desde asociaciones; `toJpaScalars` ignora `paciente`/`tipoMuestra`/`medioCultivo`/`colaboradorRegistra`/`colaboradorProcesa` para rellenarlos en el adaptador con `getReferenceById`). |

Implementaciones generadas en `target/generated-sources/annotations` (no editar a mano).

## Compilar

```bash
mvn compile
```
