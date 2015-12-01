package br.com.cwi.crescer.lavanderia.controller.material;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cwi.crescer.lavanderia.dto.MaterialDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoDTO;
import br.com.cwi.crescer.lavanderia.mapper.MaterialMapper;
import br.com.cwi.crescer.lavanderia.service.ProdutoService;

@Controller
@RequestMapping("/materiais")
public class MaterialController {

	private ProdutoService produtoService;

	@Autowired
	public MaterialController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@RequestMapping(path = "json", method = RequestMethod.GET)
	public @ResponseBody List<MaterialDTO> jsonMateriais(Long id) {

		List<ProdutoDTO> produtos = produtoService.buscarProdutosAtivosPorServico(id);
		List<MaterialDTO> dtos = new ArrayList<>();

		for (ProdutoDTO dto : produtos) {
			MaterialDTO material = MaterialMapper.toDTO(dto.getMaterial());
			dtos.add(material);
		}

		return dtos;
	}

}
