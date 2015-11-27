package br.com.cwi.crescer.lavanderia.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.dao.DAO;
import br.com.cwi.crescer.lavanderia.security.domain.Authorities;
import br.com.cwi.crescer.lavanderia.security.domain.AuthoritiesId;

@Repository
public class AuthoritiesDAO extends DAO {

	public Authorities findById(AuthoritiesId id) {
		return em.find(Authorities.class, id);
	}

	public List<Authorities> list() {
		return em.createQuery("FROM Authorities", Authorities.class).getResultList();
	}

	@Transactional
	public Authorities save(Authorities authorities) {

		Authorities foundAuthorities = findById(authorities.getAuthoritiesId());

		if (foundAuthorities == null) {
			em.persist(authorities);
		}

		return authorities;
	}

}
