package application.br.com.cwi.application;

import java.io.IOException;
import java.util.Scanner;

import list.br.com.cwi.list.linked.DoublyLinkedList;
import list.br.com.cwi.list.linked.ILinkedList;
import list.br.com.cwi.list.linked.LinkedList;
import sql.br.com.cwi.dao.ClienteDAO;
import sql.br.com.cwi.dao.DAOFactory;
import sql.br.com.cwi.dao.IDAO;
import sql.br.com.cwi.model.Cliente;
import sql.br.com.cwi.model.Pedido;
import sql.br.com.cwi.model.Servico;

public class Application {
	private LinkedList<String> linkedList;
	private DoublyLinkedList<String> doublyLinkedList;
	private ILinkedList<String> actualList;
	private Scanner scanner;
	private IDAO<Pedido> pedidoDAO;
	private IDAO<Cliente> clienteDAO;

	private void start() {

		linkedList = new LinkedList<>();
		doublyLinkedList = new DoublyLinkedList<>();
		actualList = linkedList;
		scanner = new Scanner(System.in);
		pedidoDAO = DAOFactory.createPedidoDAO();
		clienteDAO = DAOFactory.createClienteDAO();

		while (true) {
			new MenuPedidoScreen(this).show();

		}
	}

	public void changeList() {
		actualList = actualList instanceof LinkedList ? doublyLinkedList : linkedList;
	}

	public ILinkedList<String> getActualList() {
		return this.actualList;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public IDAO<Pedido> getPedidoDAO() {
		return pedidoDAO;
	}
	
	public IDAO<Cliente> getClienteDAO() {
		return clienteDAO;
	}

	public static void main(String[] args) throws IOException {

		/*
		 * IDAO<Cliente> clientDAO = DAOFactory.createClienteDAO();
		 * 
		 * IDAO<Servico> servicoDAO = DAOFactory.createServicoDAO();
		 * 
		 * IDAO<Pedido> pedidoDAO = DAOFactory.createPedidoDAO();
		 * 
		 * Cliente cliente = new Cliente(); cliente.setNmCliente("Tefferson");
		 * cliente.setNrCpf("AAAAAAAAAA");
		 * 
		 * Servico servico = new Servico(); servico.setDsServico(
		 * "Serviço de teste"); servicoDAO.insert(servico);
		 * 
		 * Pedido pedido = new Pedido(); pedido.setDsPedido("olaaaaaa");
		 * pedido.setIdCliente(3L);
		 * 
		 * System.out.println(pedidoDAO.load(5L));
		 */

		try {
			new Application().start();
		} catch (Exception e) {
			System.out.println("Erro grave! Programa finalizado.");
		}

	}
}
