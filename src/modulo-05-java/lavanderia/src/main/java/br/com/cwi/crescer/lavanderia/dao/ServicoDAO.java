package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Servico;

@Repository
public class ServicoDAO extends DAO {

	public Servico findById(Long id) {
		return em.find(Servico.class, id);
	}

	public List<Servico> list() {
		return em.createQuery("FROM Servico", Servico.class).getResultList();
	}

	public List<Servico> listWhereHasProduct() {
		return em.createQuery("FROM Servico", Servico.class).getResultList();
	}

}
