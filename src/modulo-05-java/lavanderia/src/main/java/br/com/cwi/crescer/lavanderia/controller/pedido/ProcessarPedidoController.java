package br.com.cwi.crescer.lavanderia.controller.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.dto.ItemResumoDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoIncluirItemDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;
import br.com.cwi.crescer.lavanderia.service.ItemService;
import br.com.cwi.crescer.lavanderia.service.PedidoService;

@Controller
@RequestMapping("/pedidos/processar")
public class ProcessarPedidoController extends AbstractPedidoController {

	private ItemService itemService;

	@Autowired
	public ProcessarPedidoController(PedidoService pedidoService, ItemService itemService) {
		super(pedidoService);
		this.itemService = itemService;
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewProcessa(Model model, @PathVariable("id") Long id) {

		if (pedidoService.todosItensProcessados(id)) {
			pedidoService.processar(id);
			return new ModelAndView("redirect:/pedidos/editar/" + id);
		}

		PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedidoService.buscarPedidoSituacaoProcessandoPorId(id));

		PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
		itemDTO.setIdPedido(id);

		List<ItemResumoDTO> situacaoDosItens = pedidoService.listarSituacaoDosItens(id);

		model.addAttribute("pedido", dto);
		model.addAttribute("item", itemDTO);
		model.addAttribute("situacaoDosItens", situacaoDosItens);

		return new ModelAndView("pedido/processa");
	}

	@RequestMapping(path = "{id}", method = RequestMethod.POST)
	public ModelAndView atualizarItens(@PathVariable("id") Long id,
			final RedirectAttributes redirectAttributes) {

		Item item = itemService.findById(id);
		itemService.processar(id);

		Long idPedido = item.getIdPedido();

		redirectAttributes.addFlashAttribute("mensagem", "Operação efetuada com sucesso.");
		return new ModelAndView("redirect:/pedidos/processar/" + idPedido);
	}

}