package br.com.cwi.crescer.lavanderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.lavanderia.service.*;

@Controller
public class IndexController {

	private PedidoService service;

	@Autowired
	public IndexController(PedidoService service) {
		super();
		this.service = service;
	}

	@RequestMapping({ "/", "home", "index" })
	public String index(Model model) {

		service.findById(1L).getDataEntrega();
		String mensagem = "Bem-vindo, Lavanderia Crescer!";
		model.addAttribute("mensagem", mensagem);

		return "home/index";

	}

}
