package br.com.cwi.crescer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PessoaDAO {

	@PersistenceContext
	private EntityManager em;

//	public Pessoa FindById(Long idPessoa) {
		public void FindById(Long idPessoa) {

		// String queryStr = "SELECT p FROM Pessoa p Where p.idPessoa = :id";
		//
		// TypedQuery<Pessoa> query = em.createQuery(queryStr, Pessoa.class);
		//
		// query.setParameter("id", idPessoa);
		//
		//
		//
		// return null;

//		return em.find(Pessoa.class, idPessoa);
	}
}
