package br.com.cwi.crescer.lavanderia.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.PedidoDAO;
import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;
import br.com.cwi.crescer.lavanderia.dto.ItemResumoDTO;
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

	public Pedido buscarPedidoSituacaoProcessandoPorId(Long id) {
		return pedidoDAO.findByIdAndSituacao(id, SituacaoPedido.PROCESSANDO);
	}

	public void trocarSituacaoParaProcessando(Long id) {

		Pedido pedido = buscarPedidoPorId(id);

		pedido.setSituacao(SituacaoPedido.PROCESSANDO);

		int maiorPrazo = buscarMaiorPrazo(pedido.getItens());
		Date dataEntrega = pedido.getDataInclusao();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataEntrega);
		calendar.add(Calendar.DATE, maiorPrazo);

		dataEntrega = calendar.getTime();

		pedido.setDataEntrega(dataEntrega);

		pedidoDAO.save(pedido);
	}

	private int buscarMaiorPrazo(List<Item> itens) {
		int maiorPrazo = 0;

		for (Item item : itens) {
			int prazoDoItem = item.getProduto().getPrazo();
			if (prazoDoItem > maiorPrazo) {
				maiorPrazo = prazoDoItem;
			}
		}

		return maiorPrazo;
	}

	public List<ItemResumoDTO> listarSituacaoDosItens(Long id) {
		Pedido pedido = buscarPedidoSituacaoProcessandoPorId(id);

		List<Item> itens = pedido.getItens();
		List<ItemResumoDTO> dtos = new ArrayList<>();

		for (Item item : itens) {

			ItemResumoDTO dto = new ItemResumoDTO();

			dto.setId(item.getIdItem());
			dto.setProcessado(item.isProcessado());

			dtos.add(dto);
		}

		return dtos;
	}

	public int cancelarPedido(Long id) {

		 return pedidoDAO.updateSituacao(id, SituacaoPedido.CANCELADO);
	}

	public void atualizarValorBruto(Item item) {
		Pedido pedido = pedidoDAO.findById(item.getIdPedido());

		pedido.incrementarValorBruto(item.getValorTotal());

		pedidoDAO.save(pedido);
	}

	public void processar(Long id) {

		Pedido pedido = pedidoDAO.findById(id);

		BigDecimal valorBruto = pedido.getValorBruto();

		BigDecimal porcentagemDeDesconto = calcularPorcentagemDeDesconto(pedido);

		BigDecimal desconto = valorBruto.multiply(porcentagemDeDesconto, MathContext.DECIMAL128);

		pedido.setValorDesconto(desconto);
		pedido.setValorFinal(valorBruto.subtract(desconto));

		pedido.setSituacao(SituacaoPedido.PROCESSADO);

		pedidoDAO.save(pedido);

	}

	private BigDecimal calcularPorcentagemDeDesconto(Pedido pedido) {

		BigDecimal valorBruto = pedido.getValorBruto();
		BigDecimal peso = pedido.getPesoTotalDosItens();
		BigDecimal porcentagemAtual = BigDecimal.ZERO;

		Date dataInclusao = pedido.getDataInclusao();

		Calendar c = Calendar.getInstance();
		c.setTime(dataInclusao);
		int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);

		boolean segundaAQuarta = diaDaSemana == Calendar.MONDAY || diaDaSemana == Calendar.TUESDAY || diaDaSemana == Calendar.WEDNESDAY;
		boolean quintaASexta = diaDaSemana == Calendar.THURSDAY || diaDaSemana == Calendar.FRIDAY;
		boolean pesoOuValorDaDesconto = peso.compareTo(new BigDecimal(15)) == 1				|| valorBruto.compareTo(new BigDecimal(90)) == 1;

		if (segundaAQuarta) {
			porcentagemAtual = new BigDecimal(0.08);
		} else if (pesoOuValorDaDesconto) {
			porcentagemAtual = new BigDecimal(0.0487);
		} else if (quintaASexta) {
			porcentagemAtual = new BigDecimal(0.04);
		}

		return porcentagemAtual;
	}

	public int retirar(Long id) {
		return pedidoDAO.updateSituacao(id, SituacaoPedido.ENCERRADO);
	}

	public List<PedidoResumoDTO> pesquisarPorSituacao(Integer situacao) {
		List<Pedido> pedidos = pedidoDAO.findBySituacao(SituacaoPedido.values()[situacao]);
		return PedidoMapper.toDTOList(pedidos);
	}

	public List<PedidoResumoDTO> pesquisarPorCpf(String cpf) {
		List<Pedido> pedidos = pedidoDAO.findByCpfDoCliente(cpf);
		return PedidoMapper.toDTOList(pedidos);
	}

	public boolean todosItensProcessados(Long idPedido) {
		Pedido pedido = pedidoDAO.findById(idPedido);

		for (Item item : pedido.getItens()) {
			if (!item.isProcessado()) {
				return false;
			}
		}

		return true;
	}

	public void processarTodosOsItens(Long idPedido, ItemService itemService) {

		Pedido pedido = buscarPedidoPorId(idPedido);

		for (Item item : pedido.getItens()) {
			itemService.processar(item.getIdItem());
		}

	}

}
