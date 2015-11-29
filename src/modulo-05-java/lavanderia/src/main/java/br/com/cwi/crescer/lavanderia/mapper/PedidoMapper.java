package br.com.cwi.crescer.lavanderia.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cwi.crescer.lavanderia.domain.Cliente;
import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;
import br.com.cwi.crescer.lavanderia.domain.Produto;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoResumoDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoEditarDTO;

public class PedidoMapper {

	public static Pedido getNewEntity(Long idCliente) {

		Pedido pedido = new Pedido();

		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);

		pedido.setCliente(cliente);
		pedido.setSituacao(SituacaoPedido.PENDENTE);
		pedido.setDataInclusao(new Date());
		pedido.setValorBruto(BigDecimal.ZERO);

		return pedido;
	}

	public static PedidoResumoDTO toDTO(Pedido pedido) {

		PedidoResumoDTO dto = new PedidoResumoDTO();

		dto.setCpf(pedido.getCliente().getCpf());
		dto.setDataInclusao(pedido.getDataInclusao());
		dto.setId(pedido.getIdPedido());
		dto.setNomeCliente(pedido.getCliente().getNome());
		dto.setSituacao(pedido.getSituacao());
		dto.setValorTotal(pedido.getValorFinal());

		return dto;
	}

	public static void merge(ProdutoEditarDTO produtoEditarDTO, Produto produto) {

		produto.setPrazo(produtoEditarDTO.getPrazo());
		produto.setSituacao(produtoEditarDTO.getSituacao());
		produto.setValor(produto.getValor());

	}

	public static PedidoEditarDTO toEditarDTO(Pedido pedido) {

		PedidoEditarDTO dto = new PedidoEditarDTO();

		dto.setIdPedido(pedido.getIdPedido());
		dto.setItens(pedido.getItens());

		return dto;
	}

	public static List<PedidoResumoDTO> toDTOList(List<Pedido> pedidos) {

		List<PedidoResumoDTO> dtos = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			dtos.add(toDTO(pedido));
		}

		return dtos;
	}

}
