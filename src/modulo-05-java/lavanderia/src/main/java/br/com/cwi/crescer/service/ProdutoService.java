package br.com.cwi.crescer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.ProdutoDAO;
import br.com.cwi.crescer.domain.Produto;

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
