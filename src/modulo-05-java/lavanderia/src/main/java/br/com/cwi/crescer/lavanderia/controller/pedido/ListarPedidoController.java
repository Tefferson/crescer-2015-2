package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.domain.Pedido.SituacaoPedido;
import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class ListarPedidoController extends AbstractPedidoController {

	@Autowired
	public ListarPedidoController(PedidoService pedidoService) {
		super(pedidoService);
	}

	@RequestMapping(method = RequestMethod.GET, params = {"situacao"})
	public ModelAndView viewPesquisaCpf(@RequestParam("situacao") Integer situacao) {

		return new ModelAndView("pedido/lista", "pedidos", pedidoService.pesquisarPorSituacao(situacao));

	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"cpf"})
	public ModelAndView viewPesquisaCpf(@RequestParam("cpf") String cpf) {

		return new ModelAndView("pedido/lista", "pedidos", pedidoService.pesquisarPorCpf(cpf));

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		return new ModelAndView("pedido/lista", "pedidos", pedidoService.listarPedidos());
	}
	
	@ModelAttribute("situacoes")
	public SituacaoPedido[] comboMateriais() {
		return SituacaoPedido.values();
	}
	
}
