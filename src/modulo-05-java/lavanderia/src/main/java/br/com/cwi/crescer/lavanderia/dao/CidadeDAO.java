package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Cidade;

@Repository
public class CidadeDAO extends DAO {

	public Cidade findById(Long id) {
		return em.find(Cidade.class, id);
	}

	public List<Cidade> list() {
		return em.createQuery("FROM Cidade", Cidade.class).getResultList();
	}

}
