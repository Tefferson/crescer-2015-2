package br.com.cwi.crescer.lavanderia.controller.cliente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;
import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

@Controller
@RequestMapping("/clientes/incluir")
public class IncluirClienteController extends AbstractClienteController {

	@Autowired
	public IncluirClienteController(ClienteService clienteService, CidadeService cidadeService) {
		super(clienteService, cidadeService);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView incluir(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result,
			final RedirectAttributes resirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("cliente/novo");
		}

		clienteService.incluir(clienteDTO);

		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewNovo() {

		return new ModelAndView("cliente/novo", "cliente", new ClienteDTO());
	}

}
