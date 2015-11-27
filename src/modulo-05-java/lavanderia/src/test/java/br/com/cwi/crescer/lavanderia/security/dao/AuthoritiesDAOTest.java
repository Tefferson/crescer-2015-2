package br.com.cwi.crescer.lavanderia.security.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.lavanderia.dao.AbstractInfrastructureTest;
import br.com.cwi.crescer.lavanderia.security.dao.AuthoritiesDAO;
import br.com.cwi.crescer.lavanderia.security.domain.Authorities;
import br.com.cwi.crescer.lavanderia.security.domain.AuthoritiesId;
import br.com.cwi.crescer.lavanderia.security.domain.Users;

public class AuthoritiesDAOTest extends AbstractInfrastructureTest {

	@Autowired
	private AuthoritiesDAO authoritiesDAO;

	@Test
	public void deveBuscarComAuthoritiesId() throws Exception {

		Users users = new Users();
		users.setUsername("admin");
		users.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		users.setEnabled(true);

		AuthoritiesId authoritiesId = new AuthoritiesId(users, "ROLE_ADMIN");

		Authorities authorities = authoritiesDAO.findById(authoritiesId);
		Assert.assertNotNull(authorities);
	}

}
