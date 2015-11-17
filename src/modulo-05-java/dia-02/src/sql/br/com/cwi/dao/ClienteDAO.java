package sql.br.com.cwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.br.com.cwi.jdbc.ConnectionFactory;
import sql.br.com.cwi.model.Cliente;

public class ClienteDAO {

	public void insert(Cliente cliente) {

		try {

			Connection connection = new ConnectionFactory().getConnection();

			String sql = "insert into cliente(idCliente, nmCliente, nrCpf) values(?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, cliente.getIdCliente());
			statement.setString(2, cliente.getNmCliente());
			statement.setString(3, cliente.getNrCpf());

			statement.executeQuery();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
	}

	public List<Cliente> listAll() {

		List<Cliente> clientes = new ArrayList<>();

		try {

			Connection connection = new ConnectionFactory().getConnection();

			String sql = "select idCliente, nmCliente, nrCpf from cliente";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(result.getLong("idCliente"));
				cliente.setNmCliente(result.getString("nmCliente"));
				cliente.setNrCpf(result.getString("nrCpf"));
				
				clientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return clientes;
	}

}
