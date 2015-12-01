package br.com.cwi.crescer.lavanderia.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.crescer.lavanderia.domain.Servico;
import br.com.cwi.crescer.lavanderia.dto.ServicoDTO;

public class ServicoMapper {

	public static List<ServicoDTO> toDTOList(List<Servico> servicos) {
		
		List<ServicoDTO> dtos = new ArrayList<>();

		for(Servico servico:servicos){
			dtos.add(toDTO(servico));
		}
		
		return dtos;
	}

	private static ServicoDTO toDTO(Servico servico) {
		
		ServicoDTO dto = new ServicoDTO();
		
		dto.setDescricao(servico.getDescricao());
		dto.setId(servico.getIdServico());
		
		return dto;
	}

}
