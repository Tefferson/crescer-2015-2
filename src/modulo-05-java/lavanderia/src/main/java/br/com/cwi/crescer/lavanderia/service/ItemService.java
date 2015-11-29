package br.com.cwi.crescer.lavanderia.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.ItemDAO;
import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Item.SituacaoItem;
import br.com.cwi.crescer.lavanderia.domain.Produto;
import br.com.cwi.crescer.lavanderia.dto.PedidoIncluirItemDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoDTO;

@Service
public class ItemService {

	private ItemDAO itemDAO;
	
	@Autowired
	public ItemService(ItemDAO itemDAO) {
		super();
		this.itemDAO = itemDAO;
	}

	public Item findById(Long idItem) {
		return itemDAO.findById(idItem);
	}

	public Item adicionarItemAoPedido(PedidoIncluirItemDTO pedidoIncluirItemDTO, ProdutoDTO produtoDTO) {

		Item item = new Item();
		Produto produto = new Produto();
		BigDecimal valorUnitario = produtoDTO.getValor();
		BigDecimal peso = pedidoIncluirItemDTO.getPeso();

		BigDecimal valorTotal = valorUnitario.multiply(peso);
		
		produto.setIdProduto(produtoDTO.getId());
		
		item.setIdPedido(pedidoIncluirItemDTO.getIdPedido());
		item.setSituacao(SituacaoItem.PENDENTE);
		item.setPeso(peso);
		item.setProduto(produto);
		item.setValorUnitario(valorUnitario);
		item.setValorTotal(valorTotal);
		
		return itemDAO.save(item);
		
	}

}
