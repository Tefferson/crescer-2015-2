package br.com.cwi.crescer.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.ProdutoDAO;
import br.com.cwi.crescer.lavanderia.domain.Produto;

@Service
public class ProdutoService {

	private ProdutoDAO produtoDAO;

	@Autowired
	public ProdutoService(ProdutoDAO pessoaDAO) {
		super();
		this.produtoDAO = pessoaDAO;
	}

	public Produto findById(Long idProduto) {
		return produtoDAO.findById(idProduto);
	}

}
