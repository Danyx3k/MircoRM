-- Fecha de nacimiento en lugar de edad almacenada (la edad se calcula en aplicación)

ALTER TABLE microlab.paciente
    ADD COLUMN IF NOT EXISTS fecha_nacimiento DATE;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'microlab'
          AND table_name = 'paciente'
          AND column_name = 'edad'
    ) THEN
        UPDATE microlab.paciente
        SET fecha_nacimiento = make_date(
                EXTRACT(YEAR FROM CURRENT_DATE)::int - edad,
                1,
                1
            )
        WHERE fecha_nacimiento IS NULL
          AND edad IS NOT NULL;
    END IF;
END $$;

UPDATE microlab.paciente
SET fecha_nacimiento = DATE '1900-01-01'
WHERE fecha_nacimiento IS NULL;

ALTER TABLE microlab.paciente
    ALTER COLUMN fecha_nacimiento SET NOT NULL;

ALTER TABLE microlab.paciente
    DROP COLUMN IF EXISTS edad;
