package br.com.cwi.crescer.lavanderia.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cwi.crescer.dao.ClienteDAO;
import br.com.cwi.crescer.domain.Cidade;
import br.com.cwi.crescer.domain.Cliente;
import br.com.cwi.crescer.domain.Cliente.SituacaoCliente;
import br.com.cwi.crescer.domain.Endereco;
import br.com.cwi.crescer.domain.Pedido;

public class ClienteDAOTest extends AbstractInfrastructureTest {

	@Autowired
	private ClienteDAO clienteDAO;

	@Test
	public void deveBuscarClientePorId() throws Exception {
		Cliente cliente = clienteDAO.findById(1L);
		Assert.assertNotNull(cliente);
		Assert.assertNotNull(cliente.getEndereco().getCidade());
	}

	@Test
	public void deveBuscarClientesAtivos() throws Exception {
		List<Cliente> clientes = clienteDAO.findBySituacao(SituacaoCliente.ATIVO);
		Assert.assertNotNull(clientes);
		Assert.assertFalse(clientes.isEmpty());

		for (Cliente cliente : clientes) {
			Assert.assertEquals(SituacaoCliente.ATIVO, cliente.getSituacao());
		}
	}

	@Test
	public void deveBuscarPedidos() throws Exception {
		List<Pedido> pedidos = clienteDAO.findById(1L).getPedidos();
		Assert.assertNotNull(pedidos);
	}

	@Test
	public void deveBuscarCidade() throws Exception {
		Cidade cidade = clienteDAO.findById(1L).getEndereco().getCidade();
		Assert.assertNotNull(cidade);
	}

	@Test
	public void deveBuscarEndereco() throws Exception {
		Endereco endereco = clienteDAO.findById(1L).getEndereco();
		Assert.assertNotNull(endereco);
	}
}