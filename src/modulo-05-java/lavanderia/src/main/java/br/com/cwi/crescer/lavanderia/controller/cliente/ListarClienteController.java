package br.com.cwi.crescer.lavanderia.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ListarClienteController extends AbstractClienteController {

	@Autowired
	public ListarClienteController(ClienteService clienteService, CidadeService cidadeService) {
		super(clienteService, cidadeService);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		return new ModelAndView("cliente/lista", "clientes", clienteService.listarClientes());
	}

	@RequestMapping(method = RequestMethod.GET, params = "term")
	public ModelAndView exibirBusca(@RequestParam("term") String term) {

		return new ModelAndView("cliente/lista", "clientes", clienteService.buscarPorNomeParcial(term));
	}

}
