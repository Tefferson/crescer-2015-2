package br.com.cwi.crescer.lavanderia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.ProdutoDAO;
import br.com.cwi.crescer.lavanderia.domain.Produto;
import br.com.cwi.crescer.lavanderia.domain.Produto.SituacaoProduto;
import br.com.cwi.crescer.lavanderia.dto.ProdutoDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoEditarDTO;
import br.com.cwi.crescer.lavanderia.dto.ProdutoIncluirDTO;
import br.com.cwi.crescer.lavanderia.mapper.ProdutoMapper;

@Service
public class ProdutoService {

	private ProdutoDAO produtoDAO;

	@Autowired
	public ProdutoService(ProdutoDAO pessoaDAO) {
		super();
		this.produtoDAO = pessoaDAO;
	}

	public boolean incluir(ProdutoIncluirDTO dto) {

		if (novoProdutoEhUnico(dto)) {
			Produto produto = ProdutoMapper.getNewEntity(dto);

			produto.ativar();

			produtoDAO.save(produto);

			return true;
		}

		return false;

	}

	private boolean novoProdutoEhUnico(ProdutoIncluirDTO dto) {
		return produtoDAO.findByServicoEMaterial(dto.getIdServico(), dto.getIdMaterial()) == null;
	}

	public List<ProdutoDTO> listarProdutosPorMaterialEServico(Long idMaterial, Long idServico) {

		List<Produto> produtos = new ArrayList<>();
		;

		boolean findWithMaterial = idMaterial != null && idMaterial > 0;
		boolean findWithServico = idServico != null && idServico > 0;

		boolean findWithBoth = findWithMaterial && findWithServico;

		if (findWithBoth) {
			produtos = produtoDAO.listByMaterialEServico(idMaterial, idServico);
		} else if (findWithMaterial) {
			produtos = produtoDAO.listByMaterial(idMaterial);
		} else if (findWithServico) {
			produtos = produtoDAO.listByServico(idServico);
		} else {
			produtos = produtoDAO.list();
		}

		List<ProdutoDTO> dtos = new ArrayList<>();

		for (Produto produto : produtos) {
			dtos.add(ProdutoMapper.toDTO(produto));
		}

		return dtos;
	}

	public List<ProdutoDTO> listarProdutos() {

		List<Produto> produtos = produtoDAO.list();

		List<ProdutoDTO> dtos = new ArrayList<>();

		for (Produto produto : produtos) {
			dtos.add(ProdutoMapper.toDTO(produto));
		}

		return dtos;
	}

	public ProdutoEditarDTO buscarProdutoPorId(Long idProduto) {
		Produto produto = produtoDAO.findById(idProduto);
		return ProdutoMapper.toEditarDTO(produto);
	}

	public void atualizar(ProdutoEditarDTO produtoEditarDTO) {

		Produto produto = produtoDAO.findById(produtoEditarDTO.getId());
		ProdutoMapper.merge(produtoEditarDTO, produto);

		produtoDAO.save(produto);

	}

	public Map<String, List<String>> listarProdutosComoMap() {

		List<ProdutoDTO> dtos = listarProdutos();

		Map<String, List<String>> map = new HashMap<>();

		for (ProdutoDTO dto : dtos) {

			String servico = dto.getServico().getDescricao();
			String material = dto.getMaterial().getDescricao();

			List<String> list = map.get(servico);

			if (list == null) {
				list = map.put(servico, new ArrayList<String>());
			}

			if (!list.contains(material)) {
				list.add(material);
			}

		}

		return map;

	}

	public ProdutoDTO buscarProdutoPorMaterialEServico(Long idMaterial, Long idServico) {

		Produto produto = produtoDAO.findByServicoEMaterial(idServico, idMaterial);

		return ProdutoMapper.toDTO(produto);
	}

	public List<ProdutoDTO> buscarProdutosAtivosPorServico(Long idServico) {

		List<Produto> produtos = produtoDAO.findBySituacaoAndServico(SituacaoProduto.ATIVO, idServico);

		return ProdutoMapper.toDTOList(produtos);
	}

}
