package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

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
                .setParameter("situacao", situacao)
                .getResultList();
    }

}
