package sql.br.com.cwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.br.com.cwi.dao.exception.NoRecordFoundException;
import sql.br.com.cwi.jdbc.ConnectionFactory;
import sql.br.com.cwi.model.Pedido;

public class PedidoDAO implements IDAO<Pedido> {

	@Override
	public void insert(Pedido pedido) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "insert into pedido(idpedido, idcliente, dspedido) values(pedido_seq.nextval,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, pedido.getIdCliente());
			statement.setString(2, pedido.getDsPedido());

			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
	}

	@Override
	public List<Pedido> listAll() {

		List<Pedido> pedidos = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "select idpedido, idcliente, dspedido from pedido";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Pedido pedido = new Pedido();

				pedido.setId(result.getLong("idpedido"));
				pedido.setIdCliente(result.getLong("idcliente"));
				pedido.setDsPedido(result.getString("dspedido"));

				pedidos.add(pedido);
			}

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return pedidos;
	}

	@Override
	public int update(Pedido t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long idPedido) {

		try (Connection connection = ConnectionFactory.getConnection()) {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from pedido where idPedido = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setLong(1, idPedido);
			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro SQL!!!");
		}
	}

	@Override
	public Pedido load(Long idPedido) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			Pedido pedido = new Pedido();

			StringBuilder qry = new StringBuilder();

			qry.append("select idpedido, idcliente, dspedido from pedido where idpedido=?");

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			statement.setLong(1, idPedido);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				pedido.setId(idPedido);
				pedido.setIdCliente(resultSet.getLong("idcliente"));
				pedido.setDsPedido(resultSet.getString("dspedido"));

			} else {
				throw new NoRecordFoundException();
			}

			return pedido;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}

	@Override
	public List<Pedido> find(Pedido pedidoFiltro) {
		
		try (Connection connection = ConnectionFactory.getConnection()) {

			StringBuilder qry = new StringBuilder();

			qry.append("select idpedido, idcliente, dspedido from pedido where 1=1 ");

			ArrayList<Object> parameters = new ArrayList<>();

			Object idFiltro = pedidoFiltro.getId();
			Object idClienteFiltro = pedidoFiltro.getIdCliente();
			Object dsPedidoFiltro = pedidoFiltro.getDsPedido();

			if (idFiltro != null) {
				qry.append(" and idpedido = ?");
				parameters.add(idFiltro);
			}
			
			if (idClienteFiltro != null) {
				qry.append(" and idcliente = ?");
				parameters.add(idClienteFiltro);
			}

			if (dsPedidoFiltro != null) {
				qry.append(" and dsservico = ?");
				parameters.add(dsPedidoFiltro);
			}

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			for (int i = 0; i < parameters.size(); i++) {
				statement.setObject(i + 1, parameters.get(i));
			}

			ResultSet resultSet = statement.executeQuery();

			ArrayList<Pedido> pedidos = new ArrayList<>();

			while (resultSet.next()) {

				Pedido pedido = new Pedido();
				pedido.setId(resultSet.getLong("idpedido"));
				pedido.setIdCliente(resultSet.getLong("idcliente"));
				pedido.setDsPedido(resultSet.getString("dspedido"));

				pedidos.add(pedido);

			}

			return pedidos;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}
		
}
