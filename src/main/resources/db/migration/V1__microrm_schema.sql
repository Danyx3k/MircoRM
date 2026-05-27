-- Esquema ER: Pacientes, Tipos de muestra, Medios de cultivo, Muestras (PostgreSQL)

CREATE TABLE tipos_muestra (
    id_tipo_muestra UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(120) NOT NULL,
    codigo VARCHAR(40) NOT NULL,
    tiempo_incubacion_horas INTEGER NOT NULL CHECK (tiempo_incubacion_horas >= 0),
    descripcion TEXT,
    CONSTRAINT uk_tipos_muestra_nombre UNIQUE (nombre),
    CONSTRAINT uk_tipos_muestra_codigo UNIQUE (codigo)
);

CREATE TABLE medios_cultivo (
    id_medio_cultivo UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(120) NOT NULL,
    codigo VARCHAR(40) NOT NULL,
    agar_tipo VARCHAR(120),
    temperatura_incubacion REAL NOT NULL,
    tiempo_incubacion_horas INTEGER NOT NULL CHECK (tiempo_incubacion_horas >= 0),
    composicion TEXT,
    proveedor VARCHAR(200),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_medios_cultivo_nombre UNIQUE (nombre),
    CONSTRAINT uk_medios_cultivo_codigo UNIQUE (codigo)
);

CREATE TABLE pacientes (
    id_paciente UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identificacion VARCHAR(40) NOT NULL,
    nombre_completo VARCHAR(200) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(20) NOT NULL,
    telefono VARCHAR(30),
    email VARCHAR(160),
    eps_seguro VARCHAR(120),
    observaciones_clinicas TEXT,
    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uk_pacientes_identificacion UNIQUE (identificacion)
);

CREATE TABLE muestras (
    id_muestra UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_paciente UUID NOT NULL REFERENCES pacientes (id_paciente),
    id_tipo_muestra UUID NOT NULL REFERENCES tipos_muestra (id_tipo_muestra),
    id_medio_cultivo UUID NOT NULL REFERENCES medios_cultivo (id_medio_cultivo),
    numero_laboratorio VARCHAR(40) NOT NULL,
    origen_anatomico VARCHAR(200) NOT NULL,
    fecha_hora_toma TIMESTAMPTZ NOT NULL,
    fecha_hora_recepcion TIMESTAMPTZ,
    fecha_hora_procesamiento TIMESTAMPTZ,
    estado VARCHAR(30) NOT NULL,
    cantidad_morfotipos_bacterianos INTEGER NOT NULL DEFAULT 0 CHECK (cantidad_morfotipos_bacterianos >= 0),
    es_contaminada BOOLEAN NOT NULL DEFAULT FALSE,
    observaciones_clinicas TEXT,
    observaciones_laboratorio TEXT,
    usuario_registra VARCHAR(120) NOT NULL,
    fecha_creacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    usuario_actualiza VARCHAR(120),
    CONSTRAINT uk_muestras_numero_laboratorio UNIQUE (numero_laboratorio),
    CONSTRAINT chk_muestras_estado CHECK (estado IN ('RECIBIDA', 'EN_PROCESO', 'PROCESADA', 'CONTAMINADA', 'FINALIZADA', 'RECHAZADA')),
    CONSTRAINT chk_muestras_fechas CHECK (
        (fecha_hora_recepcion IS NULL OR fecha_hora_recepcion >= fecha_hora_toma)
        AND (fecha_hora_procesamiento IS NULL OR fecha_hora_procesamiento >= fecha_hora_toma)
        AND (
            fecha_hora_procesamiento IS NULL
            OR fecha_hora_recepcion IS NULL
            OR fecha_hora_procesamiento >= fecha_hora_recepcion
        )
    )
);

CREATE INDEX idx_muestras_paciente ON muestras (id_paciente);
