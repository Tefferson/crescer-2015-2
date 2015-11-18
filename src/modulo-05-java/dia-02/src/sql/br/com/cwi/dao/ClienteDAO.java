package sql.br.com.cwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.br.com.cwi.jdbc.ConnectionFactory;
import sql.br.com.cwi.model.Cliente;

public class ClienteDAO implements IDAO<Cliente> {

	public void insert(Cliente cliente) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "insert into cliente(idCliente, nmCliente, nrCpf) values(cliente_seq.nextval,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cliente.getNmCliente());
			statement.setString(2, cliente.getNrCpf());

			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
	}

	public List<Cliente> listAll() {

		List<Cliente> clientes = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "select idCliente, nmCliente, nrCpf from cliente";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Cliente cliente = new Cliente();

				cliente.setId(result.getLong("idCliente"));
				cliente.setNmCliente(result.getString("nmCliente"));
				cliente.setNrCpf(result.getString("nrCpf"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return clientes;
	}

	public void delete(Long id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from cliente where idcliente=?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.setLong(1, id);

			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro sql!");
		}
	}

	public int update(Cliente cliente) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "update cliente idCliente=?, nmCliente=?, nrCpf=?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, cliente.getId());
			statement.setString(2, cliente.getNmCliente());
			statement.setString(3, cliente.getNrCpf());

			return statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return 0;
	}

	public Cliente load(Long idCliente) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			Cliente cliente = new Cliente();

			StringBuilder qry = new StringBuilder();

			qry.append("select idcliente, nmcliente, nrcpf from cliente where idcliente=?");

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			statement.setLong(1, idCliente);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				cliente.setId(idCliente);
				cliente.setNmCliente(resultSet.getString("nmCliente"));
				cliente.setNrCpf(resultSet.getString("nrCpf"));

			} else {
				throw new RuntimeException("Registro não encontrado");
			}

			return cliente;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}

	public List<Cliente> find(Cliente clienteFiltro) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			StringBuilder qry = new StringBuilder();

			qry.append("select idcliente, nmcliente, nrcpf from cliente where 1=1 ");

			ArrayList<Object> parameters = new ArrayList<>();

			Object idFiltro = clienteFiltro.getId();
			Object nmClienteFiltro = clienteFiltro.getNmCliente();
			Object nrCpfFiltro = clienteFiltro.getNrCpf();

			if (idFiltro != null) {
				qry.append(" and idcliente = ?");
				parameters.add(idFiltro);
			}

			if (nmClienteFiltro != null) {
				qry.append(" and nmcliente = ?");
				parameters.add(nmClienteFiltro);
			}

			if (nrCpfFiltro != null) {
				qry.append(" and nrcpf = ?");
				parameters.add(nrCpfFiltro);
			}

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			for (int i = 0; i < parameters.size(); i++) {
				statement.setObject(i + 1, parameters.get(i));
			}

			ResultSet resultSet = statement.executeQuery();

			ArrayList<Cliente> clientes = new ArrayList<>();

			while (resultSet.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getLong("idCliente"));
				cliente.setNmCliente(resultSet.getString("nmCliente"));
				cliente.setNrCpf(resultSet.getString("nrCpf"));

				clientes.add(cliente);

			}

			return clientes;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}

}
