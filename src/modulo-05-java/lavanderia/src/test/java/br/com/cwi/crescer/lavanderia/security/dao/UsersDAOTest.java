package br.com.cwi.crescer.lavanderia.security.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.lavanderia.dao.AbstractInfrastructureTest;
import br.com.cwi.crescer.lavanderia.security.dao.UsersDAO;
import br.com.cwi.crescer.lavanderia.security.domain.Users;

public class UsersDAOTest extends AbstractInfrastructureTest {

	@Autowired
	private UsersDAO usersDAO;

	@Test
	public void deveBuscarPorUsername() throws Exception {

		String username = "admin";

		Users users = usersDAO.findByUsername(username);
		Assert.assertNotNull(users);
	}

}
