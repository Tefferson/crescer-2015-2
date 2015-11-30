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
@RequestMapping("/pedidos/visualizar")
public class VisualizarPedidoController extends AbstractPedidoController {

	@Autowired
	public VisualizarPedidoController(PedidoService pedidoService) {
		super(pedidoService);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewExibe(Model model, @PathVariable("id") Long id) {

		Pedido pedido = pedidoService.buscarPedidoPorId(id);

		PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedido);

		PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
		itemDTO.setIdPedido(id);

		model.addAttribute("pedido", dto);
		model.addAttribute("item", itemDTO);

		if (pedido.isCancelado() || pedido.isEncerrado()) {
			return new ModelAndView("pedido/visualiza");
		} else {
			return new ModelAndView("pedido/visualizaComCancelar");
		}
	}

}
