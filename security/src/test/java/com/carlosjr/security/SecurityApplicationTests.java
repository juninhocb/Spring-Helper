package com.carlosjr.security;

import com.carlosjr.security.employee.Employee;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SecurityApplicationTests {

	@BeforeClass
	public static void startTests() {
		System.out.println("Starting tests");
	}

	@AfterClass
	public static void closeTests() {
		System.out.println("Closing tests");
	}

	@Before
	public void beginTesting() {
		System.out.println("Begin testing");
	}

	@After
	public void finishTesting(){
		System.out.println("Finish tests");
	}

	@Test
	void testingEmployeeObject(){
		//Assert.assertTrue(str.isEmpty());
		//Assert.assertEquals("Alexandre", nome);
		//assertThat(32).isEqualTo(32);
		Employee e = new Employee(1L, "James Brown", false);
		assertEquals("James Brown", e.getName());
		System.out.printf("The name is %s and the employee retired variable is %s", e.getName(), e.isRetired());
	}



}
