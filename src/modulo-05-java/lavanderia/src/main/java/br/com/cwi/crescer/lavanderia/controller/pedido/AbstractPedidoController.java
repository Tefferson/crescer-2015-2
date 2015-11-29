package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public abstract class AbstractPedidoController {

	protected PedidoService pedidoService;

	@Autowired
	public AbstractPedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
}
