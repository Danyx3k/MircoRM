-- Dominio identidad + trazabilidad dual colaborador en muestras (ER MicroRM completo)

CREATE TABLE roles_colaborador (
    id_rol UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(120) NOT NULL,
    codigo VARCHAR(40) NOT NULL,
    descripcion TEXT,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_roles_colaborador_nombre UNIQUE (nombre),
    CONSTRAINT uk_roles_colaborador_codigo UNIQUE (codigo)
);

CREATE TABLE colaborador (
    id_colaborador UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_rol UUID NOT NULL REFERENCES roles_colaborador (id_rol),
    username VARCHAR(120) NOT NULL,
    nombre_completo VARCHAR(200) NOT NULL,
    email VARCHAR(160) NOT NULL,
    telefono VARCHAR(30),
    cedula VARCHAR(40) NOT NULL,
    numero_licencia VARCHAR(80),
    especialidad VARCHAR(120),
    departamento VARCHAR(120),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_colaborador_username UNIQUE (username),
    CONSTRAINT uk_colaborador_email UNIQUE (email),
    CONSTRAINT uk_colaborador_cedula UNIQUE (cedula)
);

INSERT INTO roles_colaborador (id_rol, nombre, codigo, descripcion, activo)
VALUES (
    'c0000000-0000-4000-a000-000000000001',
    'Técnico de laboratorio',
    'LAB_TEC',
    'Recepción y procesamiento de muestras',
    TRUE
) ON CONFLICT (codigo) DO NOTHING;

INSERT INTO colaborador (
    id_colaborador, id_rol, username, nombre_completo, email, telefono, cedula,
    numero_licencia, especialidad, departamento, activo
)
SELECT
    'd0000000-0000-4000-a000-000000000001'::uuid,
    r.id_rol,
    'sistema.dev',
    'Colaborador Desarrollo',
    'lab.dev@hospital.local',
    '3000000000',
    'DEV-0001',
    NULL,
    'Microbiología',
    'Laboratorio Central',
    TRUE
FROM roles_colaborador r
WHERE r.codigo = 'LAB_TEC'
ON CONFLICT (username) DO NOTHING;

ALTER TABLE muestras ADD COLUMN IF NOT EXISTS id_colaborador_registra UUID REFERENCES colaborador (id_colaborador);
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS id_colaborador_procesa UUID REFERENCES colaborador (id_colaborador);
ALTER TABLE muestras ADD COLUMN IF NOT EXISTS es_contaminada BOOLEAN NOT NULL DEFAULT FALSE;

UPDATE muestras
SET id_colaborador_registra = 'd0000000-0000-4000-a000-000000000001',
    id_colaborador_procesa = 'd0000000-0000-4000-a000-000000000001'
WHERE id_colaborador_registra IS NULL OR id_colaborador_procesa IS NULL;

ALTER TABLE muestras ALTER COLUMN id_colaborador_registra SET NOT NULL;
ALTER TABLE muestras ALTER COLUMN id_colaborador_procesa SET NOT NULL;

ALTER TABLE muestras DROP CONSTRAINT IF EXISTS chk_muestras_estado;
ALTER TABLE muestras ADD CONSTRAINT chk_muestras_estado CHECK (
    estado IN ('RECIBIDA', 'EN_PROCESO', 'PROCESADA', 'CONTAMINADA', 'FINALIZADA', 'RECHAZADA')
);

ALTER TABLE muestras ALTER COLUMN usuario_registra DROP NOT NULL;

CREATE INDEX idx_muestras_colab_registra ON muestras (id_colaborador_registra);
CREATE INDEX idx_muestras_colab_procesa ON muestras (id_colaborador_procesa);
