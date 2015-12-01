package br.com.cwi.crescer.lavanderia.mapper;

import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.dto.MaterialDTO;

public class MaterialMapper {

	public static MaterialDTO toDTO(Material material) {
		MaterialDTO dto = new MaterialDTO();
		
		dto.setDescricao(material.getDescricao());
		dto.setId(material.getIdMaterial());

		return dto;
	}

}
