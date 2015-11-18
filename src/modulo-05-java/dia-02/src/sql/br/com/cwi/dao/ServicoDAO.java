package sql.br.com.cwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.br.com.cwi.jdbc.ConnectionFactory;
import sql.br.com.cwi.model.Servico;

public class ServicoDAO implements IDAO<Servico> {

	public void insert(Servico servico) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "insert into servico(idservico, dsservico) values(servico_seq.nextval,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, servico.getDsServico());

			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
	}

	public List<Servico> listAll() {

		List<Servico> servicos = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "select idServico, dsServico from servico";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Servico servico = new Servico();

				servico.setId(result.getLong("idservico"));
				servico.setDsServico(result.getString("dsservico"));

				servicos.add(servico);
			}

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return servicos;
	}

	public void delete(Long id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from servico where idservico=?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.setLong(1, id);

			statement.execute();

		} catch (SQLException e) {
			System.out.println("Erro sql!");
		}
	}

	public int update(Servico servico) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "update cliente set idCliente=?, nmCliente=?, nrCpf=?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, servico.getId());
			statement.setString(2, servico.getDsServico());

			return statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
		
		return 0;
		
	}

	public Servico load(Long idServico) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			Servico servico = new Servico();

			StringBuilder qry = new StringBuilder();

			qry.append("select idservico, dsservico from servico where idservico=?");

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			statement.setLong(1, idServico);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				servico.setId(idServico);
				servico.setDsServico(resultSet.getString("dsServico"));

			} else {
				throw new RuntimeException("Registro não encontrado");
			}

			return servico;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}

	public List<Servico> find(Servico servicoFiltro) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			StringBuilder qry = new StringBuilder();

			qry.append("select idservico, dsservico from servico where 1=1 ");

			ArrayList<Object> parameters = new ArrayList<>();

			Object idFiltro = servicoFiltro.getId();
			Object dsServicoFiltro = servicoFiltro.getDsServico();

			if (idFiltro != null) {
				qry.append(" and idcliente = ?");
				parameters.add(idFiltro);
			}

			if (dsServicoFiltro != null) {
				qry.append(" and dsservico = ?");
				parameters.add(dsServicoFiltro);
			}

			PreparedStatement statement = connection.prepareStatement(qry.toString());

			for (int i = 0; i < parameters.size(); i++) {
				statement.setObject(i + 1, parameters.get(i));
			}

			ResultSet resultSet = statement.executeQuery();

			ArrayList<Servico> servicos = new ArrayList<>();

			while (resultSet.next()) {

				Servico servico = new Servico();
				servico.setId(resultSet.getLong("idservico"));
				servico.setDsServico(resultSet.getString("dsservico"));

				servicos.add(servico);

			}

			return servicos;

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return null;
	}

}
