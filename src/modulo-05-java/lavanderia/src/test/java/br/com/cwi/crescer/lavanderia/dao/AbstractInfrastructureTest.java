package br.com.cwi.crescer.lavanderia.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.cwi.crescer.LavanderiaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LavanderiaApplication.class)
@WebAppConfiguration
public abstract class AbstractInfrastructureTest {
}