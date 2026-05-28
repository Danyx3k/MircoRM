# WAF — ModSecurity + OWASP CRS (NGINX)

Proxy inverso con [OWASP ModSecurity CRS](https://github.com/coreruleset/modsecurity-crs-docker) delante del **API Gateway**.

```
Cliente → microrm-waf (:8080) → microrm-gateway (:8080 interno) → microrm-api (:8081)
```

## Archivos

| Archivo | Uso |
|---------|-----|
| `rules/REQUEST-900-EXCLUSION-RULES-BEFORE-CRS.conf` | Exclusiones MicroRM (health, Swagger, JWT, API JSON) |
| `rules/RESPONSE-999-EXCLUSION-RULES-AFTER-CRS.conf` | Exclusiones de respuesta (opcional) |

## Variables (`docker/.env`)

| Variable | Descripción | Por defecto |
|----------|-------------|-------------|
| `WAF_PORT` | Puerto HTTP público (entrada única) | `8080` |
| `MODSEC_RULE_ENGINE` | `On`, `Off`, `DetectionOnly` | `On` |
| `MODSEC_BLOCKING_PARANOIA` | Nivel CRS (1–4) | `1` |
| `MODSEC_ANOMALY_INBOUND` | Umbral de anomalía entrante | `10` |
| `GATEWAY_DEBUG_PORT` | Si se define, expone el gateway sin WAF (solo depuración) | vacío |

Para probar sin bloquear tráfico: `MODSEC_RULE_ENGINE=DetectionOnly` y revise los logs del contenedor `microrm-waf`.

## Logs de auditoría

```powershell
docker compose logs -f microrm-waf
```
