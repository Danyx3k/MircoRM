# MicroRM

Backend Spring Boot. Documentación: [documentos/README.md](documentos/README.md).

Auth0 (JWT en `/api/**`): [documentos/auth0.md](documentos/auth0.md).

```bash
mvn spring-boot:run
```

### Si no arranca

1. **Puerto 8080 ocupado** (mensaje `Port 8080 was already in use`): cierre otra instancia de MicroRM o IDE, o en PowerShell: `Get-NetTCPConnection -LocalPort 8080 | Select OwningProcess` y termine ese PID.
2. **Auth0 / JWT**: con `AUTH_ENABLED=true` hace falta red hacia Auth0 y `AUTH0_ISSUER_URI` + `AUTH0_AUDIENCE`. Para desarrollo use `AUTH_ENABLED=false` en `application-auth0.local.properties` (se importa solo).
3. **PostgreSQL**: debe estar en marcha en `localhost:5432`, base `microrm`, usuario `microrm` / contraseña `microrm_dev` (o sus variables `DB_*`).
