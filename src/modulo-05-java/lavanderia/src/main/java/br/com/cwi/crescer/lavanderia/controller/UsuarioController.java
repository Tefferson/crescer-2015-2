package br.com.cwi.crescer.lavanderia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cwi.crescer.lavanderia.security.domain.Authorities;
import br.com.cwi.crescer.lavanderia.security.service.AuthoritiesService;
import br.com.cwi.crescer.lavanderia.security.service.UsersService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	public AuthoritiesService authoritiesService;
	public UsersService usersService;

	@Autowired
	public UsuarioController(AuthoritiesService authoritiesService, UsersService usersService) {
		this.authoritiesService = authoritiesService;
		this.usersService = usersService;
	}
	
	@RequestMapping(path="/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		return new ModelAndView("usuario/lista", "usuarios", authoritiesService.listarUsuarios());
	}

}
