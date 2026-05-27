package co.hospital.MicroRM.infrastructure.persistence.repository.adapter.sql.jdbc;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.repository.NumeroLaboratorioSequencePort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Secuencia en memoria por día (perfil {@code test}); thread-safe en una sola JVM.
 */
@Repository
@Profile("test")
public class TestNumeroLaboratorioSequenceAdapter implements NumeroLaboratorioSequencePort {

	private final Map<LocalDate, AtomicInteger> porDia = new ConcurrentHashMap<>();

	@Override
	public synchronized int reserveNextIndex(LocalDate fechaNegocio) {
		AtomicInteger contador = porDia.computeIfAbsent(fechaNegocio, d -> new AtomicInteger(0));
		int siguiente = contador.get();
		if (siguiente > 999) {
			throw MicroRMException.of(MessagesEnum.MUESTRA_CONSECUTIVO_DIARIO_AGOTADO);
		}
		contador.incrementAndGet();
		return siguiente;
	}
}
