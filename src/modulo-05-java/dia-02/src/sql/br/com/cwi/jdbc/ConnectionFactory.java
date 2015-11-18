package sql.br.com.cwi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private ConnectionFactory(){}
	
	public static Connection getConnection() throws SQLException {
		String strConexao = "jdbc:oracle:thin:@localhost:1521:xe";
		return DriverManager.getConnection(strConexao, "LAVANDERIA", "LAVANDERIA");
	}
}
