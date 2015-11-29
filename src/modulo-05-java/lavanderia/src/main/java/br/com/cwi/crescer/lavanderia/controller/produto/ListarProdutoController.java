package br.com.cwi.crescer.lavanderia.controller.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.service.MaterialService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/produtos")
public class ListarProdutoController extends AbstractProdutoController {

	@Autowired
	public ListarProdutoController(ProdutoService produtoService, MaterialService materialService,
			ServicoService servicoService) {
		super(produtoService, materialService, servicoService);
	}

	@RequestMapping(method = RequestMethod.GET, params = "idServico")
	public ModelAndView listarComFiltro(@RequestParam("idServico") Long idServico,
			@RequestParam("idMaterial") Long idMaterial) {

		return new ModelAndView("produto/lista", "produtos",
				produtoService.listarProdutosPorMaterialEServico(idMaterial, idServico));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		return new ModelAndView("produto/lista", "produtos", produtoService.listarProdutos());
	}

}