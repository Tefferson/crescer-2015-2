package br.com.cwi.crescer.lavanderia.controller.pedido;

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

import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.domain.Pedido;
import br.com.cwi.crescer.lavanderia.domain.Servico;
import br.com.cwi.crescer.lavanderia.dto.PedidoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.PedidoIncluirItemDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoDTO;
import br.com.cwi.crescer.lavanderia.mapper.PedidoMapper;
import br.com.cwi.crescer.lavanderia.service.ItemService;
import br.com.cwi.crescer.lavanderia.service.MaterialService;
import br.com.cwi.crescer.lavanderia.service.PedidoService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/pedidos/editar")
public class EditarPedidoController extends AbstractPedidoController {

	private MaterialService materialService;
	private ServicoService servicoService;
	private ProdutoService produtoService;
	private ItemService itemService;

	@Autowired
	public EditarPedidoController(PedidoService pedidoService, MaterialService materialService,
			ServicoService servicoService, ProdutoService produtoService, ItemService itemService) {
		super(pedidoService);
		this.materialService = materialService;
		this.servicoService = servicoService;
		this.produtoService = produtoService;
		this.itemService = itemService;
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewEdita(Model model, @PathVariable("id") Long id) {

		Pedido pedido = pedidoService.buscarPedidoPorId(id);

		if (pedido.isProcessando()) {
			return new ModelAndView("redirect:/pedidos/processar/" + id);
		}

		PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedido);

		PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
		itemDTO.setIdPedido(id);

		model.addAttribute("pedido", dto);
		model.addAttribute("item", itemDTO);

		if (pedido.isPendente()) {
			return new ModelAndView("pedido/edita");
		} else if (pedido.isAguardandoRetirada()) {
			return new ModelAndView("redirect:/pedidos/retirar/" + id);
		} else if (pedido.isCancelado() || pedido.isEncerrado()) {
			return new ModelAndView("pedido/editaBloqueado");
		} else {
			return new ModelAndView("pedido/exibe");
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.POST)
	public ModelAndView processandoPedido(@PathVariable("id") Long id) {

		pedidoService.trocarSituacaoParaProcessando(id);

		return new ModelAndView("redirect:/pedidos/processar/" + id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView adicionarItem(Model model,
			@Valid @ModelAttribute("item") PedidoIncluirItemDTO pedidoIncluirItemDTO, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			
			Long id = pedidoIncluirItemDTO.getIdPedido();

			Pedido pedido = pedidoService.buscarPedidoPorId(id);

			PedidoEditarDTO dto = PedidoMapper.toEditarDTO(pedido);

			PedidoIncluirItemDTO itemDTO = new PedidoIncluirItemDTO();
			itemDTO.setIdPedido(id);

			model.addAttribute("pedido", dto);
			model.addAttribute("item", itemDTO);
			return new ModelAndView("pedido/edita");
		}

		ProdutoDTO produtoDTO = produtoService.buscarProdutoPorMaterialEServico(pedidoIncluirItemDTO.getIdMaterial(),
				pedidoIncluirItemDTO.getIdServico());
		Item item = itemService.adicionarItemAoPedido(pedidoIncluirItemDTO, produtoDTO);
		pedidoService.atualizarValorBruto(item);

		redirectAttributes.addFlashAttribute("mensagem", "Item adicionado com sucesso.");

		return new ModelAndView("redirect:/pedidos/editar/" + pedidoIncluirItemDTO.getIdPedido());
	}

	@ModelAttribute("materiais")
	public List<Material> comboMateriais() {
		return materialService.listar();
	}

	@ModelAttribute("servicos")
	public List<Servico> comboServicos() {
		return servicoService.listar();
	}

}
