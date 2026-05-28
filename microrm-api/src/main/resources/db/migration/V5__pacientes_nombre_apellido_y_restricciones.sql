-- Nombre y apellido separados; identificación más corta; EPS obligatoria; elimina nombre_completo

ALTER TABLE pacientes ADD COLUMN IF NOT EXISTS nombre VARCHAR(20);
ALTER TABLE pacientes ADD COLUMN IF NOT EXISTS apellido VARCHAR(20);

UPDATE pacientes
SET
    nombre = LEFT(
            CASE
                WHEN position(' ' IN trim(nombre_completo)) > 0 THEN
                    trim(substring(trim(nombre_completo) FROM 1 FOR position(' ' IN trim(nombre_completo)) - 1))
                ELSE trim(nombre_completo)
                END,
            20
    ),
    apellido = LEFT(
            CASE
                WHEN position(' ' IN trim(nombre_completo)) > 0 THEN
                    trim(substring(trim(nombre_completo) FROM position(' ' IN trim(nombre_completo)) + 1))
                ELSE '-'
                END,
            20
    )
WHERE nombre IS NULL;

UPDATE pacientes SET identificacion = left(trim(identificacion), 15);

UPDATE pacientes SET eps_seguro = coalesce(nullif(trim(eps_seguro), ''), 'SIN_EPS');

ALTER TABLE pacientes ALTER COLUMN nombre SET NOT NULL;
ALTER TABLE pacientes ALTER COLUMN apellido SET NOT NULL;
ALTER TABLE pacientes ALTER COLUMN eps_seguro SET NOT NULL;

ALTER TABLE pacientes DROP COLUMN IF EXISTS nombre_completo;

ALTER TABLE pacientes ALTER COLUMN identificacion TYPE VARCHAR(15);
