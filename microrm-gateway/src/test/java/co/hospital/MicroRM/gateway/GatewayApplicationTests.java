package co.hospital.MicroRM.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "MICRORM_API_URI=http://127.0.0.1:18081")
class GatewayApplicationTests {

	@Test
	void contextLoads() {
	}

}
