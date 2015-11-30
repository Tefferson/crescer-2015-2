package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.service.ClienteService;
import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos/incluir")
public class IncluirPedidoController extends AbstractPedidoController {

	private ClienteService clienteService;

	@Autowired
	public IncluirPedidoController(PedidoService pedidoService, ClienteService clienteService) {
		super(pedidoService);
		this.clienteService = clienteService;
	}

	@RequestMapping(method = RequestMethod.GET, params = "idCliente")
	public ModelAndView incluir(@RequestParam("idCliente") Long idCliente,
			final RedirectAttributes redirectAttributes) {

		if (idCliente < 1) {
			return new ModelAndView("pedido/novo");
		}

		Long idPedido = pedidoService.incluir(idCliente);

		redirectAttributes.addFlashAttribute("mensagem", "Pedido cadastrado com sucesso.");
		return new ModelAndView("redirect:/pedidos/editar/" + idPedido);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewNovo() {

		return new ModelAndView("pedido/novo", "clientes", clienteService.listarNomesDosClientes());
	}

}
