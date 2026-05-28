package co.hospital.MicroRM.infrastructure.security;

import co.hospital.MicroRM.crossscutting.messagescatalog.MessagesEnum;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;

final class SecurityProblemResponseWriter {

	private SecurityProblemResponseWriter() {
	}

	static void writeJson(HttpServletResponse response, int status, String codigo, String mensaje) throws IOException {
		response.setStatus(status);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(
				"{\"codigo\":\"" + jsonEscape(codigo) + "\",\"mensaje\":\"" + jsonEscape(mensaje) + "\"}");
	}

	static void writeColaboradorNoAutorizado(HttpServletResponse response) throws IOException {
		MessagesEnum m = MessagesEnum.COLABORADOR_NO_AUTORIZADO;
		writeJson(response, HttpServletResponse.SC_FORBIDDEN, m.getCode(), m.getDefaultMessage());
	}

	private static String jsonEscape(String value) {
		if (value == null) {
			return "";
		}
		return value.replace("\\", "\\\\").replace("\"", "\\\"");
	}

}
