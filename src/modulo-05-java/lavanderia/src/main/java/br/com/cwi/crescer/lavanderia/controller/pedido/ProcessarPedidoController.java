package br.com.cwi.crescer.lavanderia.controller.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.dto.ItemResumoDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoIncluirItemDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;
import br.com.cwi.crescer.lavanderia.service.ItemService;
import br.com.cwi.crescer.lavanderia.service.PedidoService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;

@Controller
@RequestMapping("/pedidos/processar")
public class ProcessarPedidoController extends AbstractPedidoController {

	private ProdutoService produtoService;
	private ItemService itemService;

	@Autowired
	public ProcessarPedidoController(PedidoService pedidoService, ProdutoService produtoService,
			ItemService itemService) {
		super(pedidoService);
		this.produtoService = produtoService;
		this.itemService = itemService;
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewProcessa(Model model, @PathVariable("id") Long id) {

		PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedidoService.buscarPedidoSituacaoProcessandoPorId(id));

		PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
		itemDTO.setIdPedido(id);

		List<ItemResumoDTO> situacaoDosItens = pedidoService.listarSituacaoDosItens(id);

		model.addAttribute("pedido", dto);
		model.addAttribute("item", itemDTO);
		model.addAttribute("situacaoDosItens", situacaoDosItens);

		return new ModelAndView("pedido/processa");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView atualizarItens(@Valid @ModelAttribute("situacaoDosItens") ArrayList<ItemResumoDTO> situacaoDosItens,
			BindingResult result, final RedirectAttributes resirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("pedido/edita");
		}
		
		List<ItemResumoDTO> a = situacaoDosItens;
		
		return new ModelAndView("redirect:/pedidos/");
	}

}