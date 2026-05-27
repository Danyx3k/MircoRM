-- Instalaciones con columna única observaciones (V1 antiguo): reparto a observaciones_laboratorio.
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS observaciones_clinicas TEXT;
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS observaciones_laboratorio TEXT;
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS fecha_hora_procesamiento TIMESTAMPTZ;
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS usuario_actualiza VARCHAR(120);

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns c
        WHERE c.table_schema = current_schema()
          AND c.table_name = 'muestras'
          AND c.column_name = 'observaciones'
    ) THEN
        EXECUTE $sql$
            UPDATE muestras
            SET observaciones_laboratorio = observaciones
            WHERE observaciones IS NOT NULL
              AND observaciones <> ''
              AND observaciones_laboratorio IS NULL
        $sql$;
    END IF;
END $$;

ALTER TABLE muestras DROP COLUMN IF EXISTS observaciones;

ALTER TABLE muestras DROP CONSTRAINT IF EXISTS chk_muestras_fechas;
ALTER TABLE muestras ADD CONSTRAINT chk_muestras_fechas CHECK (
    (fecha_hora_recepcion IS NULL OR fecha_hora_recepcion >= fecha_hora_toma)
    AND (fecha_hora_procesamiento IS NULL OR fecha_hora_procesamiento >= fecha_hora_toma)
    AND (
        fecha_hora_procesamiento IS NULL
        OR fecha_hora_recepcion IS NULL
        OR fecha_hora_procesamiento >= fecha_hora_recepcion
    )
);
