package application.br.com.cwi.application;

import java.util.Scanner;

import sql.br.com.cwi.dao.IDAO;
import sql.br.com.cwi.model.Cliente;
import sql.br.com.cwi.model.Pedido;

public class LoadPedidoScreen implements IScreen {

	private IDAO<Pedido> pedidoDAO;
	private IDAO<Cliente> clienteDAO;
	private Scanner scanner;

	public LoadPedidoScreen(Application application) {

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

		System.out.println("Informe o número do pedido:");

		Long idPedido = scanner.nextLong();
		scanner.nextLine();

		Pedido pedido = pedidoDAO.load(idPedido);

		Cliente cliente = clienteDAO.load(pedido.getIdCliente());

		StringBuilder sb = new StringBuilder();

		sb.append(pedido.getId());
		sb.append(" - ");
		sb.append(cliente.getNmCliente());
		sb.append(" - ");
		sb.append(pedido.getDsPedido());


		System.out.println(sb.toString());
		System.out.println();
		System.out.println();
		
	}

	@Override
	public String options() {
		// TODO Auto-generated method stub
		return null;
	}

}
