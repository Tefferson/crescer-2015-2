package br.com.cwi.crescer.lavanderia.controller.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.dto.ProdutoIncluirDTO;
import br.com.cwi.crescer.lavanderia.service.MaterialService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/produtos/incluir")
public class IncluirProdutoController extends AbstractProdutoController {

	@Autowired
	public IncluirProdutoController(ProdutoService produtoService, MaterialService materialService,
			ServicoService servicoService) {
		super(produtoService, materialService, servicoService);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView incluir(@Valid @ModelAttribute("produto") ProdutoIncluirDTO produtoInluirDTO,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("produto/novo");
		}

		if (produtoService.incluir(produtoInluirDTO)) {
			return new ModelAndView("redirect:/produtos");
		}

		redirectAttributes.addFlashAttribute("mensagem", "Produto já existente. É possível apenas editar.");
		return new ModelAndView("redirect:/produtos/incluir");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewNovo() {

		return new ModelAndView("produto/novo", "produto", new ProdutoIncluirDTO());
	}

}
