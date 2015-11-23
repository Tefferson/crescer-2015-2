package br.com.cwi.crescer.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.domain.Pessoa;

@Repository
public class PessoaDAO extends DAO {

	public Pessoa findById(Long id) {
		return em.find(Pessoa.class, id);
	}

}
