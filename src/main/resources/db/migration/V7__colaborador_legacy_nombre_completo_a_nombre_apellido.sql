-- Instalaciones que aplicaron V3 antiguo (solo columna nombre_completo): pasar a nombre + apellido como en microlab real.
DO $$
BEGIN
	IF EXISTS (
		SELECT 1 FROM information_schema.columns
		WHERE table_schema = current_schema() AND table_name = 'colaborador' AND column_name = 'nombre_completo'
	) AND NOT EXISTS (
		SELECT 1 FROM information_schema.columns
		WHERE table_schema = current_schema() AND table_name = 'colaborador' AND column_name = 'nombre'
	) THEN
		ALTER TABLE colaborador ADD COLUMN nombre VARCHAR(100);
		ALTER TABLE colaborador ADD COLUMN apellido VARCHAR(100);
		UPDATE colaborador SET
			nombre = left(
				CASE
					WHEN position(' ' IN trim(nombre_completo)) > 0 THEN
						trim(substring(trim(nombre_completo) FROM 1 FOR position(' ' IN trim(nombre_completo)) - 1))
					ELSE trim(nombre_completo)
				END,
				100
			),
			apellido = left(
				CASE
					WHEN position(' ' IN trim(nombre_completo)) > 0 THEN
						trim(substring(trim(nombre_completo) FROM position(' ' IN trim(nombre_completo)) + 1))
					ELSE '-'
				END,
				100
			);
		ALTER TABLE colaborador ALTER COLUMN nombre SET NOT NULL;
		ALTER TABLE colaborador ALTER COLUMN apellido SET NOT NULL;
		ALTER TABLE colaborador DROP COLUMN nombre_completo;
	END IF;
END $$;
