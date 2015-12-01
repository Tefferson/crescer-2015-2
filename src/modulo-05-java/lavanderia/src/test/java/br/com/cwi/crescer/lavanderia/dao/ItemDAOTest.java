package br.com.cwi.crescer.lavanderia.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Produto;

public class ItemDAOTest extends AbstractInfrastructureTest {

	@Autowired
	private ItemDAO itemDAO;

	@Test
	public void deveBuscarPedidoPorId() throws Exception {
		Item item = itemDAO.findById(1L);
		Assert.assertNotNull(item);
	}

	@Test
	public void deveBuscarPedido() throws Exception {
		Long idPedido = itemDAO.findById(1L).getIdPedido();
		Assert.assertNotNull(idPedido);
	}
	
	@Test
	public void deveBuscarProduto() throws Exception {
		Produto produto = itemDAO.findById(1L).getProduto();
		Assert.assertNotNull(produto);
	}
}
