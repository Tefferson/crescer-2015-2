package application.br.com.cwi.application;

import java.util.List;
import java.util.Scanner;

import sql.br.com.cwi.dao.IDAO;
import sql.br.com.cwi.model.Cliente;
import sql.br.com.cwi.model.Pedido;

public class ListPedidoScreen implements IScreen {

	private IDAO<Pedido> pedidoDAO;
	private IDAO<Cliente> clienteDAO;
	private Scanner scanner;

	public ListPedidoScreen(Application application) {
		this.pedidoDAO = application.getPedidoDAO();
		this.clienteDAO = application.getClienteDAO();
		this.scanner = application.getScanner();
	}

	@Override
	public void show() {

		List<Cliente> clientes = clienteDAO.listAll();

		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(i + 1 + " - " + clientes.get(i).getNmCliente());
		}

		System.out.println("Informe o índice do cliente:");

		Integer selectedIndex = scanner.nextInt();
		scanner.nextLine();
		Cliente selectedCliente = clientes.get(selectedIndex - 1);

		System.out.println("Pedidos de " + selectedCliente.getNmCliente() + ":");

		Pedido pedidoFiltro = new Pedido();
		pedidoFiltro.setIdCliente(selectedCliente.getId());

		List<Pedido> pedidos = pedidoDAO.find(pedidoFiltro);

		StringBuilder sb = new StringBuilder();

		for (Pedido pedido : pedidos) {

			sb.setLength(0);

			sb.append(pedido.getId());
			sb.append(" - ");
			sb.append(pedido.getDsPedido());
			sb.append(System.lineSeparator());

			System.out.println(sb.toString());
		}

		System.out.println();
		System.out.println("Listagem de pedidos do cliente concluída!");
		System.out.println();
		System.out.println();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public String options() {
		// TODO Auto-generated method stub
		return null;
	}

}
