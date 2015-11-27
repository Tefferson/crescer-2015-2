package br.com.cwi.crescer.lavanderia.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.security.dao.AuthoritiesDAO;
import br.com.cwi.crescer.lavanderia.security.domain.Authorities;

@Service
public class AuthoritiesService {

	AuthoritiesDAO authoritiesDAO;

	@Autowired
	public AuthoritiesService(AuthoritiesDAO authoritiesDAO) {
		this.authoritiesDAO = authoritiesDAO;
	}

	public List<Authorities> listarUsuarios() {
		return authoritiesDAO.list();
	}

}
