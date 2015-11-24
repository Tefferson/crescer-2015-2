package br.com.cwi.crescer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.CidadeDAO;
import br.com.cwi.crescer.domain.Cidade;

@Service
public class CidadeService {

	private CidadeDAO cidadeDAO;

	@Autowired
	public CidadeService(CidadeDAO cidadeDAO) {
		super();
		this.cidadeDAO = cidadeDAO;
	}

	public Cidade findById(Long idCidade) {
		return cidadeDAO.findById(idCidade);
	}

	public List<Cidade> listar() {
		return cidadeDAO.list();
	}

}
