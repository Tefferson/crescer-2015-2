package br.com.cwi.crescer.lavanderia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.domain.Cidade;
import br.com.cwi.crescer.lavanderia.domain.Cliente.SituacaoCliente;
import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;
import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

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

		return new ModelAndView("cliente/lista", "clientes", clienteService.listarClientes());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView exibir(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/exibe", "cliente", clienteService.buscarClientePorId(id));
	}

	@RequestMapping(method = RequestMethod.GET, params="term")
	public ModelAndView exibirBusca(@RequestParam("term") String term) {

		return new ModelAndView("cliente/lista", "clientes", clienteService.buscarPorNomeParcial(term));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView viewEdita(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/edita", "cliente", clienteService.buscarClientePorId(id));
	}

	@RequestMapping(path = "/incluir", method = RequestMethod.POST)
	public ModelAndView incluir(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result,
			final RedirectAttributes resirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("cliente/novo");
		}

		clienteService.incluir(clienteDTO);

		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/incluir", method = RequestMethod.GET)
	public ModelAndView viewNovo() {

		return new ModelAndView("cliente/novo", "cliente", new ClienteDTO());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result,
			final RedirectAttributes resirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("cliente/edita");
		}

		clienteService.atualizar(clienteDTO);
		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/remover", method = RequestMethod.POST)
	public ModelAndView remover(ClienteDTO clienteDTO, final RedirectAttributes redirectAttributes) {

		try {
			clienteService.desativar(clienteDTO.getId());
			redirectAttributes.addFlashAttribute("mensagem", "Operação realizada com sucesso");
		} catch (Exception e) {
			// TODO: Não foi possível remover porque é fk
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

	@ModelAttribute("situacoes")
	public SituacaoCliente[] comboSituacoes() {
		return SituacaoCliente.values();
	}
}
