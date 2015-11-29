package br.com.cwi.crescer.lavanderia.controller.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.domain.Produto.SituacaoProduto;
import br.com.cwi.crescer.lavanderia.domain.Servico;
import br.com.cwi.crescer.lavanderia.service.MaterialService;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/produtos")
public abstract class AbstractProdutoController {

	public ProdutoService produtoService;
	public ServicoService servicoService;
	public MaterialService materialService;

	@Autowired
	public AbstractProdutoController(ProdutoService produtoService, MaterialService materialService,
			ServicoService servicoService) {
		this.produtoService = produtoService;
		this.servicoService = servicoService;
		this.materialService = materialService;
	}

	@ModelAttribute("situacoes")
	public SituacaoProduto[] comboSituacoes() {
		return SituacaoProduto.values();
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
