package br.com.cwi.crescer.lavanderia.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.dao.DAO;
import br.com.cwi.crescer.lavanderia.security.domain.Users;

@Repository
public class UsersDAO extends DAO {

	public Users findByUsername(String username) {
		return em.createQuery("FROM Users u WHERE u.username = :username", Users.class)
				.setParameter("username", username).getSingleResult();
	}

	public List<Users> findByEnabled(Boolean enabled) {
		return em.createQuery("FROM Users u WHERE u.enabled = :enabled", Users.class).setParameter("enabled", enabled)
				.getResultList();
	}

	public List<Users> list() {
		return em.createQuery("FROM Users", Users.class).getResultList();
	}

	@Transactional
	public Users save(Users users) {

		Users foundUsers = findByUsername(users.getUsername());

		if (foundUsers == null) {
			em.persist(users);
			return users;
		}

		return em.merge(users);
	}

}
