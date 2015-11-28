package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.crescer.lavanderia.domain.Material;

@Repository
public class MaterialDAO extends DAO {

	public Material findById(Long id) {
		return em.find(Material.class, id);
	}

	public List<Material> list() {
		return em.createQuery("FROM Material", Material.class).getResultList();
	}

}
