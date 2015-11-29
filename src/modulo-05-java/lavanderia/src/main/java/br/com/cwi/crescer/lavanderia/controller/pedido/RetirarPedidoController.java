package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoIncluirItemDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;
import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos/retirar/{id}")
public class RetirarPedidoController extends AbstractPedidoController {

	@Autowired
	public RetirarPedidoController(PedidoService pedidoService) {
		super(pedidoService);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewEdita(Model model, @PathVariable("id") Long id) {

		Pedido pedido = pedidoService.buscarPedidoPorId(id);

		if (pedido.isAguardandoRetirada()) {

			PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedido);

			PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
			itemDTO.setIdPedido(id);

			model.addAttribute("pedido", dto);
			model.addAttribute("item", itemDTO);

			return new ModelAndView("pedido/retira");
		}

		return new ModelAndView("redirect:/pedidos/");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processandoPedido(@PathVariable("id") Long id) {

		pedidoService.retirar(id);

		return new ModelAndView("redirect:/pedidos/");
	}

}
