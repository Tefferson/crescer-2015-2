package br.com.cwi.crescer.lavanderia.controller.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cwi.crescer.lavanderia.domain.Cidade;
import br.com.cwi.crescer.lavanderia.domain.Cliente.SituacaoCliente;
import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public abstract class AbstractClienteController {

	public ClienteService clienteService;
	public CidadeService cidadeService;

	@Autowired
	public AbstractClienteController(ClienteService clienteService, CidadeService cidadeService) {
		this.clienteService = clienteService;
		this.cidadeService = cidadeService;
	}
	
	@ModelAttribute("cidades")
	public List<Cidade> comboCidades() {
		return cidadeService.listar();
	}

	@ModelAttribute("situacoes")
	public SituacaoCliente[] comboSituacoes() {
		return SituacaoCliente.values();
	}
}
