package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Cliente;
import br.com.cwi.crescer.lavanderia.domain.Cliente.SituacaoCliente;

@Repository
public class ClienteDAO extends DAO {

	public Cliente findById(Long id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> findBySituacao(SituacaoCliente situacao) {
		return em.createQuery("FROM Cliente c WHERE c.situacao = :situacao", Cliente.class)
				.setParameter("situacao", situacao).getResultList();
	}

	public List<Cliente> list() {
		return em.createQuery("FROM Cliente", Cliente.class).getResultList();
	}

	@Transactional
	public Cliente save(Cliente cliente) {

		if (cliente.getIdCliente() == null) {
			em.persist(cliente);
			return cliente;
		}

		return em.merge(cliente);
	}

	@Transactional
	public void inactive(Long idCliente) {
		Cliente cliente = findById(idCliente);
		cliente.desativar();
		em.merge(cliente);
	}

	public List<Cliente> findPartialName(String term) {

		return em.createQuery("FROM Cliente where nome like :nome", Cliente.class).setParameter("nome", term + "%")
				.getResultList();
	}

	public List<Cliente> listarNomeEIdAtivos() {
		return em.createQuery("FROM Cliente c where c.situacao = :situacao", Cliente.class)
				.setParameter("situacao", SituacaoCliente.ATIVO).getResultList();
	}

	public List<Cliente> findActiveAndPartialName(String term) {
		return em.createQuery("FROM Cliente where situacao=:situacao and nome like :nome", Cliente.class)
				.setParameter("situacao", SituacaoCliente.ATIVO).setParameter("nome", term + "%").getResultList();
	}

}
