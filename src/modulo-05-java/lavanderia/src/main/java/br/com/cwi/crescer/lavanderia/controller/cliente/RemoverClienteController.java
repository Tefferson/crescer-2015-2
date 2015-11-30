package br.com.cwi.crescer.lavanderia.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;
import br.com.cwi.crescer.lavanderia.service.CidadeService;
import br.com.cwi.crescer.lavanderia.service.ClienteService;

@Controller
@RequestMapping("/clientes/remover")
public class RemoverClienteController extends AbstractClienteController {

	@Autowired
	public RemoverClienteController(ClienteService clienteService, CidadeService cidadeService) {
		super(clienteService, cidadeService);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView remover(ClienteDTO clienteDTO, final RedirectAttributes redirectAttributes) {

		clienteService.desativar(clienteDTO.getId());
		redirectAttributes.addFlashAttribute("mensagem", "Operação realizada com sucesso");

		return new ModelAndView("redirect:/clientes");
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewRemove(@PathVariable("id") Long id) {

		return new ModelAndView("cliente/remove", "cliente", clienteService.buscarClientePorId(id));
	}

}
