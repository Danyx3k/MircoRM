-- Puente: instalaciones con medios_cultivo antiguo (temperatura_celsius) o sin columnas del catálogo hospitalario.
-- No añade temperatura_celsius en greenfield; copia dinámicamente solo si esa columna existe (evita error de parseo en PG).

ALTER TABLE medios_cultivo ADD COLUMN IF NOT EXISTS temperatura_incubacion REAL;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns c
        WHERE c.table_schema = current_schema()
          AND c.table_name = 'medios_cultivo'
          AND c.column_name = 'temperatura_celsius'
    ) THEN
        EXECUTE $sql$
            UPDATE medios_cultivo
            SET temperatura_incubacion = temperatura_celsius
            WHERE temperatura_incubacion IS NULL
        $sql$;
    END IF;
END $$;

UPDATE medios_cultivo SET temperatura_incubacion = 37.0 WHERE temperatura_incubacion IS NULL;

ALTER TABLE medios_cultivo ALTER COLUMN temperatura_incubacion SET NOT NULL;

ALTER TABLE medios_cultivo ADD COLUMN IF NOT EXISTS proveedor VARCHAR(200);
ALTER TABLE medios_cultivo ADD COLUMN IF NOT EXISTS activo BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE medios_cultivo ADD COLUMN IF NOT EXISTS fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT NOW();
ALTER TABLE medios_cultivo ADD COLUMN IF NOT EXISTS fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT NOW();

-- Esquema antiguo (Flyway V1 previo): eliminar columna obsoleta tras copiar a temperatura_incubacion.
ALTER TABLE medios_cultivo DROP COLUMN IF EXISTS temperatura_celsius;
