-- La entidad JPA y el trigger de consecutivo requieren numero_laboratorio en muestra.
-- Si aplicaste el DDL hospitalario manual sin esta columna, esta migración la agrega.

ALTER TABLE microlab.muestra
    ADD COLUMN IF NOT EXISTS numero_laboratorio VARCHAR(10);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint c
        JOIN pg_namespace n ON n.oid = c.connamespace
        WHERE c.conname = 'uk_muestra_numero_laboratorio'
          AND n.nspname = 'microlab'
    ) THEN
        ALTER TABLE microlab.muestra
            ADD CONSTRAINT uk_muestra_numero_laboratorio UNIQUE (numero_laboratorio);
    END IF;
END $$;

CREATE INDEX IF NOT EXISTS ix_muestra_numero_laboratorio
    ON microlab.muestra (numero_laboratorio);
