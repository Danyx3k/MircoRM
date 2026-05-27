INSERT INTO tipos_muestra (id_tipo_muestra, nombre, codigo, tiempo_incubacion_horas, descripcion)
VALUES (
    '00000000-0000-4000-a000-000000000001',
    'Urocultivo',
    'URO',
    24,
    'Cultivo de orina'
) ON CONFLICT (codigo) DO NOTHING;

INSERT INTO medios_cultivo (
    id_medio_cultivo,
    nombre,
    codigo,
    agar_tipo,
    temperatura_incubacion,
    tiempo_incubacion_horas,
    composicion,
    proveedor,
    activo
)
VALUES (
    '00000000-0000-4000-a000-000000000002',
    'Agar MacConkey',
    'MAC',
    'MacConkey',
    37.0,
    24,
    'Peptona, lactosa, sales biliares',
    NULL,
    TRUE
) ON CONFLICT (codigo) DO NOTHING;
