# Auth0 — MicroRM (backend + frontend)

## 1. API en Auth0

1. En [Auth0 Dashboard](https://manage.auth0.com/) → **Applications** → **APIs** → **Create API**.
2. **Name:** MicroRM API (o el que prefiera).
3. **Identifier:** valor que usará como *audience* (p. ej. `https://microrm-api` o `microrm-api`). Debe coincidir en backend y frontend.
4. **Signing Algorithm:** RS256 (por defecto).

## 2. Aplicación SPA (frontend)

1. **Applications** → **Create Application** → tipo **Single Page Application**.
2. **Allowed Callback URLs:** `http://localhost:5173/callback`
3. **Allowed Logout URLs:** `http://localhost:5173/login`
4. **Allowed Web Origins:** `http://localhost:5173`
5. En **APIs**, autorice la SPA para usar la API creada (si aplica en su tenant).

## 3. Variables de entorno

### Backend (`MicroRM`)

Copie `application-auth0.example.properties` o exporte:

| Variable | Ejemplo |
|----------|---------|
| `AUTH_ENABLED` | `true` |
| `AUTH0_ISSUER_URI` | `https://TU-TENANT.us.auth0.com/` |
| `AUTH0_AUDIENCE` | `https://microrm-api` (mismo Identifier de la API) |

Desarrollo local sin JWT: `AUTH_ENABLED=false` (valor por defecto).

### Frontend (`FrontMicroRm/frontend/.env`)

```env
VITE_AUTH0_DOMAIN=TU-TENANT.us.auth0.com
VITE_AUTH0_CLIENT_ID=xxxxxxxx
VITE_AUTH0_AUDIENCE=https://microrm-api
```

`VITE_AUTH0_AUDIENCE` debe ser **idéntico** a `AUTH0_AUDIENCE` del backend.

## 4. Arranque alineado

1. Backend con Auth0: `AUTH_ENABLED=true` y issuer/audience configurados → `mvn spring-boot:run`
2. Frontend: `npm run dev` con `.env` completo (reinicie Vite tras cambiar `.env`).
3. Inicie sesión con **Continuar con Auth0**; el callback procesa el redirect y las peticiones a `/api` llevan `Authorization: Bearer …`.

## 5. Colaborador en base de datos

El correo con el que el usuario inicia sesión en Auth0 debe existir en `microlab.colaborador.correo_electronico` con `activo = true`. Si no, la API responde **403** con código **MRM-3001** y el front muestra el nombre del colaborador solo cuando la sesión es válida.

Ejemplo (ajuste correo y cargo según su tenant):

```sql
INSERT INTO microlab.colaborador (id_colaborador, id_cargo, nombre, apellido, correo_electronico, activo)
SELECT gen_random_uuid(), c.id_cargo, 'Nombre', 'Apellido', 'su-correo@dominio.com', true
FROM microlab.cargo c
WHERE NOT EXISTS (
  SELECT 1 FROM microlab.colaborador col
  WHERE lower(col.correo_electronico) = lower('su-correo@dominio.com')
)
LIMIT 1;
```

## 6. Comprobación

- Sin token, `GET http://localhost:8080/api/v1/pacientes` → **401** (con `AUTH_ENABLED=true`).
- Con token válido desde el SPA (proxy `/api`) → **200** o respuesta de negocio habitual.
