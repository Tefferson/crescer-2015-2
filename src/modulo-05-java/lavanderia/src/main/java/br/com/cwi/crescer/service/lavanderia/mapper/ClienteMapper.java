package br.com.cwi.crescer.service.lavanderia.mapper;

import br.com.cwi.crescer.domain.Cidade;
import br.com.cwi.crescer.domain.Cliente;
import br.com.cwi.crescer.domain.Endereco;
import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;

public class ClienteMapper {

	public static ClienteDTO toDTO(Cliente cliente) {

		ClienteDTO dto = new ClienteDTO();

		Endereco endereco = cliente.getEndereco();

		dto.setCpf(cliente.getCpf());
		dto.setEmail(cliente.getEmail());
		dto.setId(cliente.getIdCliente());
		dto.setNome(cliente.getNome());
		dto.setIdCidade(endereco.getCidade().getIdCidade());
		dto.setBairro(endereco.getBairro());
		dto.setCep(endereco.getCep());
		dto.setEndereco(endereco.getEndereco());

		return dto;

	}

	public static void merge(ClienteDTO clienteDTO, Cliente cliente) {

		cliente.setCpf(clienteDTO.getCpf());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNome(clienteDTO.getNome());

		Endereco endereco = cliente.getEndereco();

		endereco.setEndereco(clienteDTO.getEndereco());
		endereco.setCep(clienteDTO.getCep());
		endereco.setBairro(clienteDTO.getBairro());
		endereco.setCidade(new Cidade());
		endereco.getCidade().setIdCidade(clienteDTO.getIdCidade());
	}

	public static Cliente getNewEntity(ClienteDTO clienteDTO) {

		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setEmail(clienteDTO.getEmail());

		Endereco endereco = new Endereco();

		endereco.setEndereco(clienteDTO.getEndereco());
		endereco.setCep(clienteDTO.getCep());
		endereco.setBairro(clienteDTO.getBairro());
		endereco.setCidade(new Cidade());
		endereco.getCidade().setIdCidade(clienteDTO.getIdCidade());

		cliente.setEndereco(endereco);

		return cliente;
	}

}
