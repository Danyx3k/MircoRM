package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jdbc;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.repository.NumeroLaboratorioSequencePort;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * PostgreSQL: una fila por día; {@code INSERT ... ON CONFLICT DO UPDATE} serializa peticiones concurrentes.
 */
@Repository
@Profile("!test")
public class PostgresNumeroLaboratorioSequenceAdapter implements NumeroLaboratorioSequencePort {

	private static final String UPSERT_NEXT = """
			INSERT INTO consecutivo_muestra_diario (fecha, ultimo)
			VALUES (?, 0)
			ON CONFLICT (fecha) DO UPDATE
			SET ultimo = consecutivo_muestra_diario.ultimo + 1
			WHERE consecutivo_muestra_diario.ultimo < 999
			RETURNING ultimo
			""";

	private final JdbcTemplate jdbcTemplate;

	public PostgresNumeroLaboratorioSequenceAdapter(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int reserveNextIndex(LocalDate fechaNegocio) {
		List<Integer> result = jdbcTemplate.query(
				UPSERT_NEXT,
				(rs, rowNum) -> rs.getInt(1),
				Date.valueOf(fechaNegocio));
		if (result.isEmpty()) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_CONSECUTIVO_DIARIO_AGOTADO);
		}
		return result.get(0);
	}
}
