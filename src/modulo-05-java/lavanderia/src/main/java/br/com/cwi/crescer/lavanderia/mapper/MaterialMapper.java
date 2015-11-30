package br.com.cwi.crescer.lavanderia.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.crescer.lavanderia.domain.Material;
import br.com.cwi.crescer.lavanderia.dto.MaterialDTO;

public class MaterialMapper {

	public static List<MaterialDTO> toDTOList(List<Material> materiais) {

		List<MaterialDTO> dtos = new ArrayList<>();

		for (Material material : materiais) {
			dtos.add(toDTO(material));
		}

		return dtos;
	}

	public static MaterialDTO toDTO(Material material) {
		MaterialDTO dto = new MaterialDTO();
		
		dto.setDescricao(material.getDescricao());
		dto.setId(material.getIdMaterial());

		return dto;
	}

}
