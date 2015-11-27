package br.com.cwi.crescer.lavanderia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.cwi.crescer.lavanderia.LavanderiaApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LavanderiaApplication.class)
@WebAppConfiguration
public class LavanderiaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
