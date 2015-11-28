package br.com.cwi.crescer.lavanderia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.crescer.lavanderia.dao.ClienteDAO;
import br.com.cwi.crescer.lavanderia.domain.Cliente;
import br.com.cwi.crescer.lavanderia.dto.ClienteDTO;
import br.com.cwi.crescer.lavanderia.dto.ClienteResumoDTO;
import br.com.cwi.crescer.lavanderia.mapper.ClienteMapper;

@Service
public class ClienteService {

	private ClienteDAO clienteDAO;

	@Autowired
	public ClienteService(ClienteDAO clienteDAO) {
		super();
		this.clienteDAO = clienteDAO;
	}

	public ClienteDTO buscarClientePorId(Long idCliente) {
		Cliente cliente = clienteDAO.findById(idCliente);
		return ClienteMapper.toDTO(cliente);
	}

	public List<ClienteResumoDTO> listarClientes() {
		List<Cliente> clientes = clienteDAO.list();

		List<ClienteResumoDTO> dtos = new ArrayList<ClienteResumoDTO>();

		for (Cliente cliente : clientes) {
			dtos.add(new ClienteResumoDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(),
					cliente.getSituacao()));
		}

		return dtos;

	}

	public void atualizar(ClienteDTO clienteDTO) {

		Cliente cliente = clienteDAO.findById(clienteDTO.getId());

		ClienteMapper.merge(clienteDTO, cliente);

		clienteDAO.save(cliente);
	}

	public void incluir(ClienteDTO clienteDTO) {

		Cliente cliente = ClienteMapper.getNewEntity(clienteDTO);

		cliente.ativar();

		clienteDAO.save(cliente);
	}

	public void desativar(Long idCliente) throws Exception {

		if (idCliente != null) {
			clienteDAO.inactive(idCliente);
		}
	}

	public List<ClienteDTO> buscarPorNomeParcial(String term) {
		
		List<Cliente> clientes = clienteDAO.findPartialName(term);
		
		List<ClienteDTO> dtos = new ArrayList<ClienteDTO>();

		for (Cliente cliente : clientes) {
			dtos.add(ClienteMapper.toDTO(cliente));
		}
		
		return dtos;
	}

}
