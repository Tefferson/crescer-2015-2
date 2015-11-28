package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Produto;

@Repository
public class ProdutoDAO extends DAO {

	public Produto findById(Long id) {
		return em.find(Produto.class, id);
	}

	@Transactional
	public Produto save(Produto produto) {

		if (produto.getIdProduto() == null) {
			em.persist(produto);
			return produto;
		}

		return em.merge(produto);
	}

	public List<Produto> list() {
		return em.createQuery("FROM Produto", Produto.class).getResultList();
	}

	public Produto findByServicoEMaterial(Long idServico, Long idMaterial) {
		return em.createQuery("FROM Produto where idservico = :idservico and idmaterial = :idmaterial", Produto.class)
				.setParameter("idservico", idServico).setParameter("idmaterial", idMaterial).getSingleResult();
	}

	public List<Produto> listByMaterialEServico(Long idMaterial, Long idServico) {
		return em.createQuery("FROM Produto where idmaterial = :idmaterial and idservico = :idservico", Produto.class)
				.setParameter("idmaterial", idMaterial).setParameter("idservico", idServico).getResultList();
	}

	public List<Produto> listByMaterial(Long idMaterial) {
		return em.createQuery("FROM Produto where idmaterial = :idmaterial", Produto.class)
				.setParameter("idmaterial", idMaterial).getResultList();
	}

	public List<Produto> listByServico(Long idServico) {
		return em.createQuery("FROM Produto where idservico = :idservico", Produto.class)
				.setParameter("idservico", idServico).getResultList();
	}

}
