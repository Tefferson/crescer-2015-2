package br.com.cwi.crescer.lavanderia.controller.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cwi.crescer.lavanderia.dto.ServicoDTO;
import br.com.cwi.crescer.lavanderia.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

	private ServicoService servicoService;

	@Autowired
	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@RequestMapping(path = "json", method = RequestMethod.GET)
	public @ResponseBody List<ServicoDTO> jsonMateriais(Long id) {

		List<ServicoDTO> dtos = servicoService.listarDTO();

		return dtos;
	}

}
