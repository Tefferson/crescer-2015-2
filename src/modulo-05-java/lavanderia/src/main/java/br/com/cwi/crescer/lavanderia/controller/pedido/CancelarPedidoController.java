package br.com.cwi.crescer.lavanderia.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;
import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos/cancelar")
public class CancelarPedidoController extends AbstractPedidoController {

	@Autowired
	public CancelarPedidoController(PedidoService pedidoService) {
		super(pedidoService);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewCancela(Model model, @PathVariable("id") Long id) {

		Pedido pedido = pedidoService.buscarPedidoPorId(id);

		PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedido);

		model.addAttribute("pedido", dto);

		return new ModelAndView("pedido/cancela");
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.POST)
	public ModelAndView cancelar(Model model, @PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {

		pedidoService.cancelarPedido(id);
		
		redirectAttributes.addFlashAttribute("mensagem", "Operação efetuada com sucesso.");
		return new ModelAndView("redirect:/pedidos/");
	}

}
