INSERT INTO tipos_muestra (id_tipo_muestra, nombre, codigo, tiempo_incubacion_horas, descripcion)
VALUES (
    '00000000-0000-4000-a000-000000000001',
    'Urocultivo',
    'URO',
    24,
    'Cultivo de orina'
) ON CONFLICT (codigo) DO NOTHING;

INSERT INTO medios_cultivo (id_medio_cultivo, nombre, codigo, temperatura_celsius, tiempo_incubacion_horas, composicion, agar_tipo)
VALUES (
    '00000000-0000-4000-a000-000000000002',
    'Agar MacConkey',
    'MAC',
    37.0,
    24,
    'Peptona, lactosa, sales biliares',
    'MacConkey'
) ON CONFLICT (codigo) DO NOTHING;
