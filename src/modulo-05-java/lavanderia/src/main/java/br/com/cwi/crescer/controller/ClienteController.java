package br.com.cwi.crescer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.domain.Cidade;
import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;
import br.com.cwi.crescer.service.CidadeService;
import br.com.cwi.crescer.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	public ClienteService clienteService;
	public CidadeService cidadeService;

	@Autowired
	public ClienteController(ClienteService clienteService, CidadeService cidadeService) {
		this.clienteService = clienteService;
		this.cidadeService = cidadeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		return new ModelAndView("cliente/lista", "clientes", clienteService.listarClientesAtivos());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView exibir(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/exibe", "cliente", clienteService.buscarClientePorId(id));
	}

	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView viewEdita(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/edita", "cliente", clienteService.buscarClientePorId(id));
	}

	@RequestMapping(path = "/incluir", method = RequestMethod.POST)
	public ModelAndView incluir(ClienteDTO clienteDTO) {

		clienteService.incluir(clienteDTO);

		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/incluir", method = RequestMethod.GET)
	public ModelAndView viewNovo() {

		return new ModelAndView("cliente/novo", "cliente", new ClienteDTO());
	}

	@RequestMapping(path = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(ClienteDTO clienteDTO) {

		clienteService.atualizar(clienteDTO);
		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/remover", method = RequestMethod.POST)
	public ModelAndView remover(ClienteDTO clienteDTO, final RedirectAttributes redirectAttributes) {

		try {
			clienteService.remover(clienteDTO.getId());
			redirectAttributes.addFlashAttribute("mensagem", "Operação realizada com sucesso");
		} catch (Exception e) {
			//TODO: Não foi possível remover porque é fk 
		}
		
		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView viewRemove(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/remove", "cliente", clienteService.buscarClientePorId(id));
	}

	@ModelAttribute("cidades")
	public List<Cidade> comboCidades() {
		return cidadeService.listar();
	}
}