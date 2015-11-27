package br.com.cwi.crescer.lavanderia.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Produto;

@Repository
public class ProdutoDAO extends DAO {

	public Produto findById(Long id) {
		return em.find(Produto.class, id);
	}

}
