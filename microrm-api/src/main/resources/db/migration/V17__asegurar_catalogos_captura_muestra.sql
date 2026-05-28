-- Datos mínimos para registrar muestras (estado Recibida + colaborador activo de captura)

INSERT INTO microlab.cargo (nombre) VALUES
    ('Administrador del Sistema')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO microlab.estado (nombre) VALUES
    ('Recibida')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO microlab.colaborador (id_colaborador, id_cargo, nombre, apellido, correo_electronico, activo)
SELECT
    'b0000000-0000-4000-b000-000000000001'::uuid,
    c.id_cargo,
    'Sistema',
    'MicroRM',
    'sistema@microlab.local',
    TRUE
FROM microlab.cargo c
WHERE c.nombre = 'Administrador del Sistema'
  AND NOT EXISTS (
      SELECT 1 FROM microlab.colaborador col
      WHERE col.id_colaborador = 'b0000000-0000-4000-b000-000000000001'::uuid
  )
LIMIT 1;
