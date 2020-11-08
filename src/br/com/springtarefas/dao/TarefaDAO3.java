package br.com.springtarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.springtarefas.jdbc.ConnectionFactory;
import br.com.springtarefas.modelo.Tarefa3;


public class TarefaDAO3 {
	Connection connection;
	
	public TarefaDAO3(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Tarefa3 tarefa3){
		String sql = "insert into tarefas (descricao) " +
					"values (?)";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa3.getDescricao());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new RuntimeException (e);
		}
	}
	
	public List<Tarefa3> lista() {
		try {			
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();
			
			List<Tarefa3> tarefas3 = new ArrayList<Tarefa3>();
			while (rs.next()) {
				// adiciona a tarefa na lista
				tarefas3.add(populaTarefa(rs));
			}

			rs.close();
			stmt.close();

			return tarefas3;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Tarefa3 populaTarefa(ResultSet rs) throws SQLException {
		Tarefa3 tarefa3 = new Tarefa3();

		// popula o objeto tarefa
		tarefa3.setId(rs.getLong("id"));
		tarefa3.setDescricao(rs.getString("descricao"));
		tarefa3.setFinalizado(rs.getBoolean("finalizado"));

		// popula a data de finalizacao da tarefa, fazendo a conversao
		Date dataFinalizacao = rs.getDate("dataFinalizacao");
		if (dataFinalizacao != null) {			
			tarefa3.setDataFinalizacao(dataFinalizacao.toString());

		}
		return tarefa3;
	}
	
	public Tarefa3 buscaPorId(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return populaTarefa(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Tarefa3 tarefa3) {
		String sql = "update tarefas set descricao = ?, finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa3.getDescricao());
			stmt.setBoolean(2, tarefa3.isFinalizado());
			stmt.setDate(3, tarefa3.getDataFinalizacao() != null ? 
					Date.valueOf(tarefa3.getDataFinalizacao()) : null);
			stmt.setLong(4, tarefa3.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Tarefa3 tarefa3) {

		if (tarefa3.getId() == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		String sql = "delete from tarefas where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa3.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}