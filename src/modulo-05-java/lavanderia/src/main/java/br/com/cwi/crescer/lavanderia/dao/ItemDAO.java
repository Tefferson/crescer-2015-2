package br.com.cwi.crescer.lavanderia.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Item;
import br.com.cwi.crescer.lavanderia.domain.Item.SituacaoItem;

@Repository
public class ItemDAO extends DAO {

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

	@Transactional
	public Item save(Item item) {

		if (item.getIdItem() == null) {
			em.persist(item);
			return item;
		}

		return em.merge(item);
	}

	@Transactional
	public void processar(Long id) {
		em.createQuery("UPDATE Item i set i.situacao = :situacao WHERE idItem=:idItem")
				.setParameter("situacao", SituacaoItem.PROCESSADO).setParameter("idItem", id).executeUpdate();
	}

}
