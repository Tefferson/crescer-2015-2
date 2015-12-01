package br.com.cwi.crescer.lavanderia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.ServicoDAO;
import br.com.cwi.crescer.lavanderia.domain.Servico;

@Service
public class ServicoService {

	private ServicoDAO servicoDAO;

	@Autowired
	public ServicoService(ServicoDAO pessoaDAO) {
		super();
		this.servicoDAO = pessoaDAO;
	}

	public List<Servico> listar() {
		return servicoDAO.list();
	}

}
