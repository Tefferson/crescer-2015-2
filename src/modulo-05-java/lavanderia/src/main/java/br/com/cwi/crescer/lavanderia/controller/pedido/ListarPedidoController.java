package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class ListarPedidoController extends AbstractPedidoController {

	@Autowired
	public ListarPedidoController(PedidoService pedidoService) {
		super(pedidoService);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		return new ModelAndView("pedido/lista", "pedidos", pedidoService.listarPedidos());
	}
}
