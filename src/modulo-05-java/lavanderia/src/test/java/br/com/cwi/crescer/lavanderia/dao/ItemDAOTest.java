package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.dao.ItemDAO;
import br.com.cwi.crescer.domain.Item;
import br.com.cwi.crescer.domain.Item.SituacaoItem;
import br.com.cwi.crescer.domain.Pedido;
import br.com.cwi.crescer.domain.Produto;

public class ItemDAOTest extends AbstractInfrastructureTest {

	@Autowired
	private ItemDAO itemDAO;

	@Test
	public void deveBuscarPedidoPorId() throws Exception {
		Item item = itemDAO.findById(1L);
		Assert.assertNotNull(item);
	}

	@Test
	public void deveBuscarItensPendentes() throws Exception {
		List<Item> itens = itemDAO.findBySituacao(SituacaoItem.PENDENTE);
		Assert.assertNotNull(itens);
		Assert.assertFalse(itens.isEmpty());

		for (Item item : itens) {
			Assert.assertEquals(SituacaoItem.PENDENTE, item.getSituacao());
		}
	}

	@Test
	public void deveBuscarPedido() throws Exception {
		Pedido pedido = itemDAO.findById(1L).getPedido();
		Assert.assertNotNull(pedido);
	}
	
	@Test
	public void deveBuscarProduto() throws Exception {
		Produto produto = itemDAO.findById(1L).getProduto();
		Assert.assertNotNull(produto);
	}
}
