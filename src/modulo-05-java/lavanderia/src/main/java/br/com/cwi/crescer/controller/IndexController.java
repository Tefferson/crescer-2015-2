package br.com.cwi.crescer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.service.PessoaService;

@Controller
public class IndexController {

	private PessoaService pessoaService;

	@Autowired
	public IndexController(PessoaService pessoaService){
		super();
		this.pessoaService = pessoaService;
	}
	
	@RequestMapping("/")
	public String index(Model model){
		
		String mensagem = "Bem-vindo, Lavanderia Crescer!";
		
		model.addAttribute("mensagem", mensagem);
		
		return "index";
		
	}
	
}
