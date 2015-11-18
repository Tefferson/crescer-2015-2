package sql.br.com.cwi.dao;

import sql.br.com.cwi.model.Pedido;

public class DAOFactory {
	
	public static ClienteDAO createClienteDAO(){
		return new ClienteDAO();
	}
	
	public static ServicoDAO createServicoDAO(){
		return new ServicoDAO();
	}

	public static IDAO<Pedido> createPedidoDAO() {
		return new PedidoDAO();
	}
	
}
