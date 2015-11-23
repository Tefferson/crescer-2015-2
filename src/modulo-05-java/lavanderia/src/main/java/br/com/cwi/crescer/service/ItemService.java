package br.com.cwi.crescer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.dao.ItemDAO;
import br.com.cwi.crescer.domain.Item;

@Service
public class ItemService {

	private ItemDAO itemDAO;

	@Autowired
	public ItemService(ItemDAO itemDAO) {
		super();
		this.itemDAO = itemDAO;
	}

	public Item findById(Long idItem) {
		return itemDAO.findById(idItem);
	}

}
