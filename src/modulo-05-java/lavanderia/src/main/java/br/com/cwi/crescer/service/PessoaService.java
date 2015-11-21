package br.com.cwi.crescer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.PessoaDAO;

@Service
public class PessoaService {
	
	private PessoaDAO pessoaDAO;
	
	@Autowired
	public PessoaService(PessoaDAO pessoaDAO){
		super();
		this.pessoaDAO = pessoaDAO;
	}
	
	public String buscarNomeComLogicaComplexa(Long id){
		return pessoaDAO.findById(id).getNome();
	}
}
