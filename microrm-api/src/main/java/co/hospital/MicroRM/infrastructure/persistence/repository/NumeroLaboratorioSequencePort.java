package co.hospital.MicroRM.infrastructure.persistence.repository;

import java.time.LocalDate;

/**
 * Reserva el siguiente índice del día (0..999) de forma segura ante concurrencia.
 */
public interface NumeroLaboratorioSequencePort {

	/**
	 * @param fechaNegocio fecha civil según reloj de negocio (medianoche = reinicio)
	 * @return índice asignado para ese día (0 = sufijo 000)
	 */
	int reserveNextIndex(LocalDate fechaNegocio);
}
