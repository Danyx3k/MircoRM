-- Catálogo tipo_muestra + FK en muestra (reemplaza columna nombre en muestra)

CREATE TABLE IF NOT EXISTS microlab.tipo_muestra (
    id_tipo_muestra UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS ix_tipo_muestra_nombre ON microlab.tipo_muestra (nombre);

INSERT INTO microlab.tipo_muestra (nombre) VALUES
    ('Hemocultivo'),
    ('Coprocultivo'),
    ('Urocultivo'),
    ('Cultivo de secreción'),
    ('Esputo'),
    ('Hisopado faríngeo')
ON CONFLICT (nombre) DO NOTHING;

ALTER TABLE microlab.muestra
    ADD COLUMN IF NOT EXISTS id_tipo_muestra UUID;

-- Si existía la columna nombre (V13), intentar enlazar por coincidencia de texto
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'microlab' AND table_name = 'muestra' AND column_name = 'nombre'
    ) THEN
        UPDATE microlab.muestra m
        SET id_tipo_muestra = t.id_tipo_muestra
        FROM microlab.tipo_muestra t
        WHERE m.id_tipo_muestra IS NULL
          AND lower(trim(m.nombre)) = lower(trim(t.nombre));
    END IF;
END $$;

UPDATE microlab.muestra m
SET id_tipo_muestra = (
    SELECT t.id_tipo_muestra FROM microlab.tipo_muestra t ORDER BY t.nombre LIMIT 1
)
WHERE m.id_tipo_muestra IS NULL;

ALTER TABLE microlab.muestra
    ALTER COLUMN id_tipo_muestra SET NOT NULL;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint c
        JOIN pg_namespace n ON n.oid = c.connamespace
        WHERE c.conname = 'fk_muestra_tipo_muestra' AND n.nspname = 'microlab'
    ) THEN
        ALTER TABLE microlab.muestra
            ADD CONSTRAINT fk_muestra_tipo_muestra
            FOREIGN KEY (id_tipo_muestra)
            REFERENCES microlab.tipo_muestra (id_tipo_muestra)
            ON DELETE RESTRICT;
    END IF;
END $$;

ALTER TABLE microlab.muestra DROP COLUMN IF EXISTS nombre;
