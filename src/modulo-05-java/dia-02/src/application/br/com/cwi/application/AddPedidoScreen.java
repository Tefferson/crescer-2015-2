package application.br.com.cwi.application;

import java.util.List;
import java.util.Scanner;

import sql.br.com.cwi.dao.IDAO;
import sql.br.com.cwi.model.Cliente;
import sql.br.com.cwi.model.Pedido;

public class AddPedidoScreen implements IScreen {

	private IDAO<Pedido> pedidoDAO;
	private IDAO<Cliente> clienteDAO;
	private Scanner scanner;

	public AddPedidoScreen(Application application) {
		this.pedidoDAO = application.getPedidoDAO();
		this.clienteDAO = application.getClienteDAO();
		this.scanner = application.getScanner();
	}

	@Override
	public void show() {
		action();
	}

	@Override
	public void action() {

		List<Cliente> clientes = clienteDAO.listAll();

		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(i + 1 + " - " + clientes.get(i).getNmCliente());
		}

		System.out.println("Informe o índice do cliente:");

		Integer selectedIndex = scanner.nextInt();
		scanner.nextLine();
		Long idCliente = clientes.get(selectedIndex - 1).getId();

		System.out.println("Informe a descrição do pedido:");

		String dsPedido = scanner.nextLine();

		Pedido pedido = new Pedido();
		pedido.setIdCliente(idCliente);
		pedido.setDsPedido(dsPedido);

		pedidoDAO.insert(pedido);

		System.out.println("Pedido incluído com sucesso!");
		System.out.println();
		System.out.println();
	}

	@Override
	public String options() {
		// TODO Auto-generated method stub
		return null;
	}

}
