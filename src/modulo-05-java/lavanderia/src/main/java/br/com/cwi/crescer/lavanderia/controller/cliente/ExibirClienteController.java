package br.com.cwi.crescer.lavanderia.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.controller.AbstractClienteController;
import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ExibirClienteController extends AbstractClienteController {

	@Autowired
	public ExibirClienteController(ClienteService clienteService, CidadeService cidadeService) {
		super(clienteService, cidadeService);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView exibir(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/exibe", "cliente", clienteService.buscarClientePorId(id));
	}
}
