package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;

@Repository
public class PedidoDAO extends DAO {

	public Pedido findById(Long id) {
		return em.find(Pedido.class, id);
	}

	public List<Pedido> findBySituacao(SituacaoPedido situacao) {
		return em.createQuery("FROM Pedido c WHERE c.situacao = :situacao", Pedido.class)
				.setParameter("situacao", situacao).getResultList();
	}

	public List<Pedido> list() {
		return em.createQuery("FROM Pedido", Pedido.class).getResultList();
	}

	@Transactional
	public Pedido save(Pedido pedido) {

		if (pedido.getIdPedido() == null) {
			em.persist(pedido);
			return pedido;
		}

		return em.merge(pedido);
	}

	public Pedido findByIdAndSituacao(Long id, SituacaoPedido situacao) {
		return em.createQuery("FROM Pedido where idPedido=:idPedido and situacao=:situacao", Pedido.class)
				.setParameter("idPedido", id).setParameter("situacao", situacao).getSingleResult();
	}

	@Transactional
	public int updateSituacao(Long id, SituacaoPedido situacao) {
		return em.createQuery("UPDATE Pedido set situacao=:situacao where idPedido=:idPedido")
				.setParameter("situacao", situacao)
				.setParameter("idPedido", id)
				.executeUpdate();
	}

}
