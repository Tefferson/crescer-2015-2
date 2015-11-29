package br.com.cwi.crescer.lavanderia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.PedidoDAO;
import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoResumoDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;

@Service
public class PedidoService {

	private PedidoDAO pedidoDAO;

	@Autowired
	public PedidoService(PedidoDAO pedidoDAO) {
		super();
		this.pedidoDAO = pedidoDAO;
	}

	public Pedido buscarPedidoPorId(Long idPedido) {
		return pedidoDAO.findById(idPedido);
	}

	public List<PedidoResumoDTO> listarPedidos() {

		List<Pedido> pedidos = pedidoDAO.list();

		return PedidoMapper.toDTOList(pedidos);

	}

	public Long incluir(Long idCliente) {

		Pedido pedido = PedidoMapper.getNewEntity(idCliente);

		return pedidoDAO.save(pedido).getIdPedido();

	}

	public void atualizar(PedidoEditarDTO pedidoEditarDTO) {
		
	}

	public Pedido buscarPedidoPendentePorId(Long id) {
			return pedidoDAO.findByIdAndSituacao(id, SituacaoPedido.PENDENTE);
	}

	public Pedido buscarPedidoSituacaoProcessandoPorId(Long id) {
		return pedidoDAO.findByIdAndSituacao(id, SituacaoPedido.PROCESSANDO);
	}

	public void trocarSituacaoParaProcessando(Long id) {
		pedidoDAO.updateSituacao(id, SituacaoPedido.PROCESSANDO);
	}

	public List<PedidoResumoDTO> listarPedidosPendentes() {
		
		List<Pedido> pedidos = pedidoDAO.findBySituacao(SituacaoPedido.PENDENTE);

		return PedidoMapper.toDTOList(pedidos);
	}
	
}
