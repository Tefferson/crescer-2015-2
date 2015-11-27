package br.com.cwi.crescer.lavanderia.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Servico;

@Repository
public class ServicoDAO extends DAO {

	public Servico findById(Long id) {
		return em.find(Servico.class, id);
	}

}
