package br.com.cwi.crescer.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.domain.Item;

@Repository
public class ItemDAO extends DAO {

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

}
