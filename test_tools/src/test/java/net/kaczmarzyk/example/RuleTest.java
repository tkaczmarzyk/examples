package net.kaczmarzyk.example;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;

public class RuleTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();
	
	@Test
	public void test() throws IOException {
		File tempFile = temp.newFile();
	}
	
	@Rule
	public Timeout timeout = new Timeout(200);
	
	@Ignore // will fail due to a timeout
	@Test
	public void infiniteLoop() {
		int i = 0;
		for (;;) {
			i++;
		}
	}

	@Rule
	public TestName name = new TestName();

	@Test
	public void testName() {
		assertEquals("testName", name.getMethodName());
	}
}
