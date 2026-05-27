package co.hospital.MicroRM.infrastructure.persistence.query;

import co.hospital.MicroRM.crossscutting.exception.MicroRMException;
import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import co.hospital.MicroRM.infrastructure.persistence.sql.ColaboradorJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.EpsJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.EstadoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.SexoJpaRepository;
import co.hospital.MicroRM.infrastructure.persistence.sql.TipoDocumentoJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class MicrolabCatalogResolver {

	private static final UUID COLABORADOR_SISTEMA = UUID.fromString("b0000000-0000-4000-b000-000000000001");

	private final TipoDocumentoJpaRepository tipoDocumentoJpaRepository;
	private final SexoJpaRepository sexoJpaRepository;
	private final EpsJpaRepository epsJpaRepository;
	private final EstadoJpaRepository estadoJpaRepository;
	private final ColaboradorJpaRepository colaboradorJpaRepository;

	public MicrolabCatalogResolver(
			TipoDocumentoJpaRepository tipoDocumentoJpaRepository,
			SexoJpaRepository sexoJpaRepository,
			EpsJpaRepository epsJpaRepository,
			EstadoJpaRepository estadoJpaRepository,
			ColaboradorJpaRepository colaboradorJpaRepository) {
		this.tipoDocumentoJpaRepository = tipoDocumentoJpaRepository;
		this.sexoJpaRepository = sexoJpaRepository;
		this.epsJpaRepository = epsJpaRepository;
		this.estadoJpaRepository = estadoJpaRepository;
		this.colaboradorJpaRepository = colaboradorJpaRepository;
	}

	public UUID resolveTipoDocumento(String codigo) {
		String c = codigo == null || codigo.isBlank() ? "CC" : codigo.trim();
		return tipoDocumentoJpaRepository.findByCodigoIgnoreCase(c)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR))
				.getIdTipoDocumento();
	}

	public UUID resolveSexo(String sexoNombre) {
		String normalized = normalizeSexoNombre(sexoNombre);
		return sexoJpaRepository.findByNombreIgnoreCase(normalized)
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR))
				.getIdSexo();
	}

	public UUID resolveEps(String codigo) {
		return epsJpaRepository.findByCodigoIgnoreCase(codigo.trim())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR))
				.getIdEps();
	}

	public UUID estadoRecibidaId() {
		return estadoJpaRepository.findByNombreIgnoreCase("Recibida")
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_ESTADO_INVALIDO))
				.getIdEstado();
	}

	public UUID colaboradorCapturaMuestra() {
		if (colaboradorJpaRepository.existsByIdColaboradorAndActivoTrue(COLABORADOR_SISTEMA)) {
			return COLABORADOR_SISTEMA;
		}
		return colaboradorJpaRepository.findFirstByActivoTrueOrderByFechaCreacionAsc()
				.map(c -> c.getIdColaborador())
				.orElseThrow(() -> MicroRMException.of(MessagesEnum.MUESTRA_COLABORADOR_REGISTRA_NO_ACTIVO));
	}

	private static String normalizeSexoNombre(String sexo) {
		if (sexo == null || sexo.isBlank()) {
			throw MicroRMException.of(MessagesEnum.COMMON_VALIDATION_ERROR);
		}
		String s = sexo.trim().toUpperCase(Locale.ROOT);
		return switch (s) {
			case "MASCULINO", "M" -> "Masculino";
			case "FEMENINO", "F" -> "Femenino";
			case "OTRO", "O" -> "Otro";
			default -> {
				String title = sexo.trim().substring(0, 1).toUpperCase(Locale.ROOT)
						+ sexo.trim().substring(1).toLowerCase(Locale.ROOT);
				yield title;
			}
		};
	}
}
