package com.drawer.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Runner.class })
class AppApplicationTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@MockBean
	private Canvas canvas;

	@MockBean
	private Runner runner;

	@Before
	public void setup() {
		System.setIn(new ByteArrayInputStream("C 15 15".getBytes()));
		//System.setOut(new PrintStream(outContent));
	}

	@Test
	public void contextLoads() {
		assertNotNull(canvas);
		assertNotNull(runner);
	}

	@Test
	public void enterInvalidInstruction() throws Exception {
		System.setOut(new PrintStream(outContent));
		String[] command = {"A", "2", "3", "4", "5"};
		runner.line(command);
		assertEquals("", outContent.toString().trim());
		System.setOut(System.out);
	} 

}
