package sql.br.com.cwi.dao;

public class DAOFactory {
	
	public static ClienteDAO createClienteDAO(){
		return new ClienteDAO();
	}
	
	public static ServicoDAO createServicoDAO(){
		return new ServicoDAO();
	}
	
}
