package br.com.cwi.crescer.lavanderia.service;

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

	public Servico findById(Long idServico) {
		return servicoDAO.findById(idServico);
	}

}
