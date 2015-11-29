package br.com.cwi.crescer.lavanderia.dto;

import java.util.List;

import br.com.cwi.crescer.lavanderia.domain.Item;

public class PedidoEditarDTO {

	private Long idPedido;

	private List<Item> itens;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	
}
