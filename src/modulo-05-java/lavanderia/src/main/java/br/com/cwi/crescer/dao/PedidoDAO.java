package br.com.cwi.crescer.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.domain.Pedido;

@Repository
public class PedidoDAO extends DAO {

	public Pedido findById(Long id) {
		return em.find(Pedido.class, id);
	}

}
