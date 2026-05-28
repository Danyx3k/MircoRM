-- Catálogo de orígenes anatómicos (autocomplete en registro de muestras)
CREATE TABLE origenes_anatomicos (
    id_origen_anatomico UUID PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    codigo VARCHAR(40),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_origenes_anatomicos_nombre UNIQUE (nombre)
);

INSERT INTO origenes_anatomicos (id_origen_anatomico, nombre, codigo, activo) VALUES
    ('a0000000-0000-4000-a000-000000000001', 'Tracto respiratorio superior', 'TRS', TRUE),
    ('a0000000-0000-4000-a000-000000000002', 'Sangre', 'SAN', TRUE),
    ('a0000000-0000-4000-a000-000000000003', 'Orina', 'ORI', TRUE),
    ('a0000000-0000-4000-a000-000000000004', 'Heces', 'HEC', TRUE),
    ('a0000000-0000-4000-a000-000000000005', 'Herida', 'HER', TRUE),
    ('a0000000-0000-4000-a000-000000000006', 'Líquido cefalorraquídeo', 'LCR', TRUE)
ON CONFLICT (nombre) DO NOTHING;

-- Más tipos de muestra para búsqueda predictiva
INSERT INTO tipos_muestra (id_tipo_muestra, nombre, codigo, tiempo_incubacion_horas, descripcion) VALUES
    ('00000000-0000-4000-a000-000000000010', 'Hemocultivo', 'HEM', 48, 'Cultivo de sangre'),
    ('00000000-0000-4000-a000-000000000011', 'Coprocultivo', 'COP', 48, 'Cultivo de heces'),
    ('00000000-0000-4000-a000-000000000012', 'Cultivo de secreción', 'SEC', 24, 'Secreción de herida u otro sitio')
ON CONFLICT (codigo) DO NOTHING;

-- Campos que ya no se capturan en el registro inicial (colaboradores, medio)
ALTER TABLE muestras ALTER COLUMN id_medio_cultivo DROP NOT NULL;
ALTER TABLE muestras ALTER COLUMN id_colaborador_registra DROP NOT NULL;
ALTER TABLE muestras ALTER COLUMN id_colaborador_procesa DROP NOT NULL;
