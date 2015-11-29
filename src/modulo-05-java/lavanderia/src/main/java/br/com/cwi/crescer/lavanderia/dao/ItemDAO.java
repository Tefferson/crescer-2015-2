package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Item.SituacaoItem;

@Repository
public class ItemDAO extends DAO {

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findBySituacao(SituacaoItem situacao) {
		return em.createQuery("FROM Item c WHERE c.situacao = :situacao", Item.class).setParameter("situacao", situacao)
				.getResultList();
	}

	@Transactional
	public Item save(Item item) {

		if (item.getIdItem() == null) {
			em.persist(item);
			return item;
		}

		return em.merge(item);
	}

}
