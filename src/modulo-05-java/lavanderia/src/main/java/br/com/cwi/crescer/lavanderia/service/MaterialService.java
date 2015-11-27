package br.com.cwi.crescer.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.MaterialDAO;
import br.com.cwi.crescer.lavanderia.domain.Material;

@Service
public class MaterialService {

	private MaterialDAO materialDAO;

	@Autowired
	public MaterialService(MaterialDAO materialDAO) {
		super();
		this.materialDAO = materialDAO;
	}

	public Material findById(Long idMaterial) {
		return materialDAO.findById(idMaterial);
	}

}
