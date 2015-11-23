package br.com.cwi.crescer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.service.*;

@Controller
public class IndexController {

	private PedidoService service;

	@Autowired
	public IndexController(PedidoService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public String index(Model model) {

		String mensagem = "Bem-vindo, Lavanderia Crescer!" + service.findById(5L).getDataEntrega();
		model.addAttribute("mensagem", mensagem);

		return "index";

	}

}
