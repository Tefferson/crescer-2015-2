package sql.br.com.cwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.br.com.cwi.jdbc.ConnectionFactory;
import sql.br.com.cwi.model.Servico;

public class ServicoDAO implements IDAO<Servico>{

	public void insert(Servico servico) {

		try {

			Connection connection = new ConnectionFactory().getConnection();

			String sql = "insert into cliente(idCliente, nmCliente, nrCpf) values(?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, servico.getIdServico());
			statement.setString(2, servico.getDsServico());

			statement.executeQuery();

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}
	}

	public List<Servico> listAll() {

		List<Servico> servicos = new ArrayList<>();

		try {

			Connection connection = new ConnectionFactory().getConnection();

			String sql = "select idServico, dsServico from servico";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Servico servico = new Servico();

				servico.setIdServico(result.getLong("idCliente"));
				servico.setDsServico(result.getString("nmCliente"));
				
				servicos.add(servico);
			}

		} catch (SQLException e) {
			System.out.println("Erro sql!!!");
		}

		return servicos;
	}

}
