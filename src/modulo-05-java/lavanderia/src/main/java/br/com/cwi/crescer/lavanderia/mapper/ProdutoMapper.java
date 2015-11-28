package br.com.cwi.crescer.lavanderia.mapper;

import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.domain.Produto;
import br.com.cwi.crescer.lavanderia.domain.Servico;
import br.com.cwi.crescer.lavanderia.dto.ProdutoDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoIncluirDTO;

public class ProdutoMapper {

	public static Produto getNewEntity(ProdutoIncluirDTO dto) {

		Produto produto = new Produto();

		Material material = new Material();
		material.setIdMaterial(dto.getIdMaterial());

		Servico servico = new Servico();
		servico.setIdServico(dto.getIdServico());
		
		produto.setPrazo(dto.getPrazo());
		produto.setValor(dto.getValor());
		produto.setMaterial(material);
		produto.setServico(servico);

		return produto;
	}

	public static ProdutoDTO toDTO(Produto produto) {

		ProdutoDTO dto = new ProdutoDTO();

		dto.setId(produto.getIdProduto());
		dto.setMaterial(produto.getMaterial());
		dto.setPrazo(produto.getPrazo());
		dto.setServico(produto.getServico());
		dto.setSituacao(produto.getSituacao());
		dto.setValor(produto.getValor());

		return dto;
	}

	public static void merge(ProdutoEditarDTO produtoEditarDTO, Produto produto) {

		produto.setPrazo(produtoEditarDTO.getPrazo());
		produto.setSituacao(produtoEditarDTO.getSituacao());
		produto.setValor(produto.getValor());

	}

}
