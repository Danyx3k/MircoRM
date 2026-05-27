-- Esquema hospitalario microlab (reemplaza tablas legacy MicroRM V1–V10)
-- Incluye corrección: columna numero_laboratorio en muestra (requerida por el trigger)

CREATE SCHEMA IF NOT EXISTS microlab;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Legacy (plural / catálogos antiguos)
DROP TABLE IF EXISTS microlab.muestras CASCADE;
DROP TABLE IF EXISTS microlab.pacientes CASCADE;
DROP TABLE IF EXISTS microlab.muestra_paciente CASCADE;
DROP TABLE IF EXISTS microlab.muestra CASCADE;
DROP TABLE IF EXISTS microlab.paciente CASCADE;
DROP TABLE IF EXISTS microlab.tipos_muestra CASCADE;
DROP TABLE IF EXISTS microlab.medios_cultivo CASCADE;
DROP TABLE IF EXISTS microlab.origenes_anatomicos CASCADE;
DROP TABLE IF EXISTS microlab.consecutivo_muestra_diario CASCADE;
DROP TABLE IF EXISTS microlab.roles_colaborador CASCADE;

CREATE TABLE microlab.cargo (
    id_cargo UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX ix_cargo_nombre ON microlab.cargo(nombre);
CREATE INDEX ix_cargo_activo ON microlab.cargo(activo);

CREATE TABLE microlab.tipo_documento (
    id_tipo_documento UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX ix_tipo_documento_codigo ON microlab.tipo_documento(codigo);

CREATE TABLE microlab.sexo (
    id_sexo UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE microlab.eps (
    id_eps UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX ix_eps_codigo ON microlab.eps(codigo);
CREATE INDEX ix_eps_nombre ON microlab.eps(nombre);

CREATE TABLE microlab.sitio_anatomico (
    id_sitio_anatomico UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE microlab.estado (
    id_estado UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE microlab.colaborador (
    id_colaborador UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_cargo UUID NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_crea VARCHAR(100),
    usuario_actualiza VARCHAR(100),
    CONSTRAINT fk_colaborador_cargo
        FOREIGN KEY (id_cargo) REFERENCES microlab.cargo(id_cargo) ON DELETE RESTRICT
);

CREATE INDEX ix_colaborador_cargo ON microlab.colaborador(id_cargo);
CREATE INDEX ix_colaborador_apellido ON microlab.colaborador(apellido);
CREATE INDEX ix_colaborador_nombre ON microlab.colaborador(nombre);
CREATE INDEX ix_colaborador_activo ON microlab.colaborador(activo);

CREATE TABLE microlab.paciente (
    id_paciente UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_tipo_documento UUID NOT NULL,
    id_sexo UUID NOT NULL,
    id_eps UUID NOT NULL,
    id_colaborador_registra UUID,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    numero_identificacion VARCHAR(20) NOT NULL UNIQUE,
    celular VARCHAR(15),
    correo VARCHAR(100),
    edad INTEGER,
    observacion_clinica TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_crea VARCHAR(100),
    CONSTRAINT fk_paciente_tipo_documento
        FOREIGN KEY (id_tipo_documento) REFERENCES microlab.tipo_documento(id_tipo_documento) ON DELETE RESTRICT,
    CONSTRAINT fk_paciente_sexo
        FOREIGN KEY (id_sexo) REFERENCES microlab.sexo(id_sexo) ON DELETE RESTRICT,
    CONSTRAINT fk_paciente_eps
        FOREIGN KEY (id_eps) REFERENCES microlab.eps(id_eps) ON DELETE RESTRICT,
    CONSTRAINT fk_paciente_colaborador
        FOREIGN KEY (id_colaborador_registra) REFERENCES microlab.colaborador(id_colaborador) ON DELETE SET NULL
);

CREATE INDEX ix_paciente_numero_identificacion ON microlab.paciente(numero_identificacion);
CREATE INDEX ix_paciente_tipo_documento ON microlab.paciente(id_tipo_documento);
CREATE INDEX ix_paciente_sexo ON microlab.paciente(id_sexo);
CREATE INDEX ix_paciente_eps ON microlab.paciente(id_eps);
CREATE INDEX ix_paciente_apellido ON microlab.paciente(apellido);
CREATE INDEX ix_paciente_nombre ON microlab.paciente(nombre);

CREATE TABLE microlab.muestra (
    id_muestra UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_sitio_anatomico UUID NOT NULL,
    id_estado UUID NOT NULL,
    id_colaborador UUID NOT NULL,
    numero_laboratorio VARCHAR(10) UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    fecha_recepcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_hora_toma TIMESTAMP,
    observaciones_laboratorio TEXT,
    usuario_registra VARCHAR(100),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_crea VARCHAR(100),
    usuario_actualiza VARCHAR(100),
    CONSTRAINT fk_muestra_sitio_anatomico
        FOREIGN KEY (id_sitio_anatomico) REFERENCES microlab.sitio_anatomico(id_sitio_anatomico) ON DELETE RESTRICT,
    CONSTRAINT fk_muestra_estado
        FOREIGN KEY (id_estado) REFERENCES microlab.estado(id_estado) ON DELETE RESTRICT,
    CONSTRAINT fk_muestra_colaborador
        FOREIGN KEY (id_colaborador) REFERENCES microlab.colaborador(id_colaborador) ON DELETE RESTRICT
);

CREATE INDEX ix_muestra_sitio_anatomico ON microlab.muestra(id_sitio_anatomico);
CREATE INDEX ix_muestra_estado ON microlab.muestra(id_estado);
CREATE INDEX ix_muestra_colaborador ON microlab.muestra(id_colaborador);
CREATE INDEX ix_muestra_fecha_recepcion ON microlab.muestra(fecha_recepcion DESC);
CREATE INDEX ix_muestra_numero_laboratorio ON microlab.muestra(numero_laboratorio);

CREATE TABLE microlab.muestra_paciente (
    id_muestra_paciente UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_muestra UUID NOT NULL,
    id_paciente UUID NOT NULL,
    fecha_asociacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_muestra_paciente_muestra
        FOREIGN KEY (id_muestra) REFERENCES microlab.muestra(id_muestra) ON DELETE CASCADE,
    CONSTRAINT fk_muestra_paciente_paciente
        FOREIGN KEY (id_paciente) REFERENCES microlab.paciente(id_paciente) ON DELETE CASCADE,
    CONSTRAINT uk_muestra_paciente UNIQUE (id_muestra, id_paciente)
);

CREATE INDEX ix_muestra_paciente_muestra ON microlab.muestra_paciente(id_muestra);
CREATE INDEX ix_muestra_paciente_paciente ON microlab.muestra_paciente(id_paciente);

CREATE TABLE microlab.secuencias_diarias (
    id_secuencia_diaria UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fecha_secuencia DATE NOT NULL UNIQUE,
    contador_diario INTEGER DEFAULT 0,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX ix_secuencias_diarias_fecha ON microlab.secuencias_diarias(fecha_secuencia);

CREATE OR REPLACE FUNCTION microlab.fn_generar_numero_laboratorio()
RETURNS VARCHAR AS
$$
DECLARE
    v_hoy DATE;
    v_contador INTEGER;
    v_numero_laboratorio VARCHAR;
BEGIN
    v_hoy := CURRENT_DATE;

    INSERT INTO microlab.secuencias_diarias (fecha_secuencia, contador_diario)
    VALUES (v_hoy, 0)
    ON CONFLICT (fecha_secuencia) DO NOTHING;

    UPDATE microlab.secuencias_diarias
    SET contador_diario = contador_diario + 1,
        fecha_actualizacion = CURRENT_TIMESTAMP
    WHERE fecha_secuencia = v_hoy
    RETURNING contador_diario INTO v_contador;

    IF v_contador > 999 THEN
        RAISE EXCEPTION 'Límite diario alcanzado';
    END IF;

    v_numero_laboratorio := TO_CHAR(v_hoy, 'YYMMDD') || LPAD(v_contador::TEXT, 3, '0');
    RETURN v_numero_laboratorio;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION microlab.fn_asignar_numero_laboratorio()
RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.numero_laboratorio IS NULL OR NEW.numero_laboratorio = '' THEN
        NEW.numero_laboratorio := microlab.fn_generar_numero_laboratorio();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_asignar_numero_laboratorio ON microlab.muestra;
CREATE TRIGGER trg_asignar_numero_laboratorio
    BEFORE INSERT ON microlab.muestra
    FOR EACH ROW
    EXECUTE FUNCTION microlab.fn_asignar_numero_laboratorio();

INSERT INTO microlab.cargo (nombre) VALUES
    ('Auxiliar de Enfermería'),
    ('Bacteriólogo/Microbiólogo'),
    ('Coordinador del Laboratorio'),
    ('Administrador del Sistema');

INSERT INTO microlab.tipo_documento (nombre, codigo) VALUES
    ('Cédula de Ciudadanía', 'CC'),
    ('Pasaporte', 'PA'),
    ('Tarjeta de Identidad', 'TI'),
    ('Documento de Extranjería', 'DE');

INSERT INTO microlab.sexo (nombre) VALUES
    ('Masculino'),
    ('Femenino'),
    ('Otro');

INSERT INTO microlab.eps (nombre, codigo) VALUES
    ('EPS Sanitaria', 'EPS001'),
    ('Salud Total', 'EPS002'),
    ('Coomeva', 'EPS003'),
    ('Nueva EPS', 'EPS004');

INSERT INTO microlab.sitio_anatomico (nombre) VALUES
    ('Orina'),
    ('Sangre'),
    ('Esputo'),
    ('Secreción'),
    ('Materia Fecal'),
    ('Líquido Cefalorraquídeo'),
    ('Líquido Pleural'),
    ('Pus'),
    ('Cateter');

INSERT INTO microlab.estado (nombre) VALUES
    ('Recibida'),
    ('En Proceso'),
    ('Incubando'),
    ('Contaminada'),
    ('Rechazada'),
    ('Finalizada');

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
LIMIT 1;
