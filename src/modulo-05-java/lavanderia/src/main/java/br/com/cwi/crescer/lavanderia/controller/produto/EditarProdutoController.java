package br.com.cwi.crescer.lavanderia.controller.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.controller.AbstractProdutoController;
import br.com.cwi.crescer.lavanderia.dto.ProdutoEditarDTO;
import br.com.cwi.crescer.lavanderia.service.MaterialService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/produtos/editar")
public class EditarProdutoController extends AbstractProdutoController {

	@Autowired
	public EditarProdutoController(ProdutoService produtoService, MaterialService materialService,
			ServicoService servicoService) {
		super(produtoService, materialService, servicoService);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ModelAndView viewEdita(@PathVariable("id") Long id) {

		return new ModelAndView("produto/edita", "produto", produtoService.buscarProdutoPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView editar(@Valid @ModelAttribute("produto") ProdutoEditarDTO produtoEditarDTO,
			BindingResult result, final RedirectAttributes resirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("produto/edita");
		}

		produtoService.atualizar(produtoEditarDTO);
		return new ModelAndView("redirect:/produtos");
	}

}
