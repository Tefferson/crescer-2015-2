package br.com.cwi.crescer.lavanderia.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.security.dao.UsersDAO;
import br.com.cwi.crescer.lavanderia.security.domain.Users;

@Service
public class UsersService {

	private UsersDAO usersDAO;

	@Autowired
	public UsersService(UsersDAO usersDAO) {
		super();
		this.usersDAO = usersDAO;
	}

	public Users FindByUserName(String username) {
		return usersDAO.findByUsername(username);
	}

	public List<Users> list() {
		return usersDAO.list();
	}
}
