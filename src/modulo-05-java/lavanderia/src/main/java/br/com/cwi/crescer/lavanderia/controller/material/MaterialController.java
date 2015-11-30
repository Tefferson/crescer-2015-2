package br.com.cwi.crescer.lavanderia.controller.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.dto.MaterialDTO;
import br.com.cwi.crescer.lavanderia.mapper.MaterialMapper;
import br.com.cwi.crescer.lavanderia.service.MaterialService;

@Controller
@RequestMapping("/material")
public abstract class MaterialController {

	protected MaterialService materialService;

	@Autowired
	public MaterialController(MaterialService materialService) {
		this.materialService = materialService;
	}

	@RequestMapping(path = "/json/materiais", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<MaterialDTO> jsonMateriais() {
		List<Material> materiais = materialService.listar();
		List<MaterialDTO> dtos = MaterialMapper.toDTOList(materiais);
		return dtos;
	}

}
