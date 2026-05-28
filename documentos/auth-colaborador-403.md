# Error 403 (Forbidden) en MicroRM

## Qué significa

En el navegador, **403** en llamadas a `/api/v1/...` casi siempre es el código **MRM-3001** (*Colaborador no autorizado*):

1. Hay token Auth0 válido, pero el **correo no está** en `microlab.colaborador` con `activo = true`, o
2. El token **no trae correo** (falta scope `email` o `/userinfo` no responde).

Sin token → **401**. Sin token en rutas protegidas → **401**.

## Comprobar en DevTools

Abra **Red** → pestaña que falla → URL y cuerpo de respuesta:

- `{"codigo":"MRM-3001",...}` → registrar colaborador (abajo).
- `401` / `MRM-3000` → volver a iniciar sesión con Auth0.

## Registrar su usuario Auth0 en la BD

Sustituya `tu-correo@ejemplo.com` por el correo con el que entra en Auth0:

```sql
INSERT INTO microlab.colaborador (id_colaborador, id_cargo, nombre, apellido, correo_electronico, activo)
SELECT gen_random_uuid(),
       c.id_cargo,
       'Nombre',
       'Apellido',
       'tu-correo@ejemplo.com',
       TRUE
FROM microlab.cargo c
WHERE c.nombre = 'Administrador Del Sistema'
  AND NOT EXISTS (
    SELECT 1 FROM microlab.colaborador x
    WHERE lower(x.correo_electronico) = lower('tu-correo@ejemplo.com')
  )
LIMIT 1;
```

Con Docker:

```powershell
docker compose exec postgres psql -U microrm -d microrm -c "TU SQL AQUÍ"
```

Reinicie sesión en el front tras el INSERT.

## Auth0

En el SPA debe pedir scope `openid profile email` (ya en `auth0AuthorizationParams`). En Auth0, la API debe permitir leer el perfil del usuario.

## WAF

Si el 403 es HTML (nginx/ModSecurity) y no JSON `MRM-3001`, revise logs:

```powershell
docker compose logs --tail=50 microrm-waf
```
