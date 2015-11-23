package br.com.cwi.crescer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.PessoaDAO;
import br.com.cwi.crescer.domain.Pessoa;

@Service
public class PessoaService {

	private PessoaDAO pessoaDAO;

	@Autowired
	public PessoaService(PessoaDAO pessoaDAO) {
		super();
		this.pessoaDAO = pessoaDAO;
	}

	public Pessoa findById(Long idPessoa) {
		return pessoaDAO.findById(idPessoa);
	}

}
