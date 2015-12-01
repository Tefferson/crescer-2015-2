package br.com.cwi.crescer.lavanderia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.CidadeDAO;
import br.com.cwi.crescer.lavanderia.domain.Cidade;

@Service
public class CidadeService {

	private CidadeDAO cidadeDAO;

	@Autowired
	public CidadeService(CidadeDAO cidadeDAO) {
		super();
		this.cidadeDAO = cidadeDAO;
	}

	public List<Cidade> listar() {
		return cidadeDAO.list();
	}

}
