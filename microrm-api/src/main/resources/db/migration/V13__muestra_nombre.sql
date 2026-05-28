ALTER TABLE microlab.muestra
    ADD COLUMN IF NOT EXISTS nombre VARCHAR(100);

UPDATE microlab.muestra
SET nombre = COALESCE(NULLIF(TRIM(nombre), ''), numero_laboratorio, 'Muestra')
WHERE nombre IS NULL OR TRIM(nombre) = '';

ALTER TABLE microlab.muestra
    ALTER COLUMN nombre SET NOT NULL;
