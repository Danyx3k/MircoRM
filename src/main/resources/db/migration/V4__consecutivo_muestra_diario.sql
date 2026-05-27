-- Consecutivo diario global para número de laboratorio (AAMMDD + 000-999), atómico por fila/día
CREATE TABLE consecutivo_muestra_diario (
    fecha DATE NOT NULL PRIMARY KEY,
    ultimo SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT chk_consecutivo_muestra_diario_ultimo CHECK (ultimo >= 0 AND ultimo <= 999)
);

COMMENT ON TABLE consecutivo_muestra_diario IS 'Contador por día civil (zona negocio en app); ultimo = último índice asignado 0..999';
