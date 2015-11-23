package br.com.cwi.crescer.dao;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.domain.Material;

@Repository
public class MaterialDAO extends DAO {

	public Material findById(Long id) {
		return em.find(Material.class, id);
	}

}
