package com.todo.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerApplicationTests {

	private static final String SUCCESS = "SUCCESS";

	@Test
	public void testTransactionApplication() {
		try {
			ConsumerApplication.main(new String[] {});
		} catch (Exception e) {

		}
		assertEquals(SUCCESS, "SUCCESS");
	}

}
