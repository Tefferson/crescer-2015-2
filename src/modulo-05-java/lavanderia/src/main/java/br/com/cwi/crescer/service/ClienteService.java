package br.com.cwi.crescer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.ClienteDAO;
import br.com.cwi.crescer.domain.Cliente;

@Service
public class ClienteService {

	private ClienteDAO clienteDAO;

	@Autowired
	public ClienteService(ClienteDAO clienteDAO) {
		super();
		this.clienteDAO = clienteDAO;
	}

	public Cliente findById(Long idCliente) {
		return clienteDAO.findById(idCliente);
	}

}
