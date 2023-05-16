package com.carlosjr.flyaway;

import com.carlosjr.flyaway.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class FlyawayApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void lombokTest(){
		User u = new User(2L,"pedro", 22, false);
		u.setEnabled(true);
		System.out.println(u);
	}
}
