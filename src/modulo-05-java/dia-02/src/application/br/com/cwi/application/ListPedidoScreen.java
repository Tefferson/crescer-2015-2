package application.br.com.cwi.application;

import java.util.List;

import sql.br.com.cwi.dao.IDAO;
import sql.br.com.cwi.dao.PedidoDAO;
import sql.br.com.cwi.model.Pedido;

public class ListPedidoScreen implements IScreen {

	private IDAO<Pedido> pedidoDAO;

	public ListPedidoScreen(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	@Override
	public void show() {

		System.out.println("Listagem de pedidos:");
		System.out.println();

		//TODO:
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
