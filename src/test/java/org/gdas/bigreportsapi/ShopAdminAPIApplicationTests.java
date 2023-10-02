package org.gdas.bigreportsapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ShopAdminAPIApplication.class)
@ActiveProfiles("TESTING")
//@DataJpaTest
class ShopAdminAPIApplicationTests {

	@Test
	void contextLoads() {
	}

}
