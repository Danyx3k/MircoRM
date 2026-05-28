package co.hospital.MicroRM.infrastructure.persistence.controler.catalogo;

import co.hospital.MicroRM.infrastructure.persistence.mapper.dto.CatalogoItemResponse;
import co.hospital.MicroRM.infrastructure.persistence.query.CatalogoConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Catálogos", description = "Catálogos microlab para formularios y autocomplete")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
@RestController
@RequestMapping("/api/v1/catalogos")
public class CatalogoApiController {

	private final CatalogoConsultaService catalogoConsultaService;

	public CatalogoApiController(CatalogoConsultaService catalogoConsultaService) {
		this.catalogoConsultaService = catalogoConsultaService;
	}

	@Operation(summary = "Sitios anatómicos (autocomplete)")
	@GetMapping("/sitios-anatomicos")
	public ResponseEntity<List<CatalogoItemResponse>> sitiosAnatomicos(@RequestParam(required = false) String q) {
		return ResponseEntity.ok(catalogoConsultaService.buscarSitiosAnatomicos(q));
	}

	@Operation(summary = "Tipos de muestra (autocomplete)")
	@GetMapping("/tipos-muestra")
	public ResponseEntity<List<CatalogoItemResponse>> tiposMuestra(@RequestParam(required = false) String q) {
		return ResponseEntity.ok(catalogoConsultaService.buscarTiposMuestra(q));
	}

	@Operation(summary = "Tipos de documento")
	@GetMapping("/tipos-documento")
	public ResponseEntity<List<CatalogoItemResponse>> tiposDocumento() {
		return ResponseEntity.ok(catalogoConsultaService.listarTiposDocumento());
	}

	@Operation(summary = "Sexos")
	@GetMapping("/sexos")
	public ResponseEntity<List<CatalogoItemResponse>> sexos() {
		return ResponseEntity.ok(catalogoConsultaService.listarSexos());
	}

	@Operation(summary = "EPS")
	@GetMapping("/eps")
	public ResponseEntity<List<CatalogoItemResponse>> eps() {
		return ResponseEntity.ok(catalogoConsultaService.listarEps());
	}
}
