package br.com.springtarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.springtarefas.jdbc.ConnectionFactory;
import br.com.springtarefas.modelo.Tarefa2;


public class TarefaDAO2 {
	Connection connection;
	
	public TarefaDAO2(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Tarefa2 tarefa2){
		String sql = "insert into tarefas (descricao) " +
					"values (?)";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa2.getDescricao());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new RuntimeException (e);
		}
	}
	
	public List<Tarefa2> lista() {
		try {			
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();
			
			List<Tarefa2> tarefas2 = new ArrayList<Tarefa2>();
			while (rs.next()) {
				// adiciona a tarefa na lista
				tarefas2.add(populaTarefa(rs));
			}

			rs.close();
			stmt.close();

			return tarefas2;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Tarefa2 populaTarefa(ResultSet rs) throws SQLException {
		Tarefa2 tarefa2 = new Tarefa2();

		// popula o objeto tarefa
		tarefa2.setId(rs.getLong("id"));
		tarefa2.setDescricao(rs.getString("descricao"));
		tarefa2.setFinalizado(rs.getBoolean("finalizado"));

		// popula a data de finalizacao da tarefa, fazendo a conversao
		Date data = rs.getDate("dataFinalizacao");
		if (data != null) {
			String dataFinalizacao = new SimpleDateFormat("dd/MM/yyyy")
			.format(data);
			tarefa2.setDataFinalizacao(dataFinalizacao);			
		}
		return tarefa2;
	}
	
	public Tarefa2 buscaPorId(Long id) {

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

	public void altera(Tarefa2 tarefa2) {
		String sql = "update tarefas set descricao = ?, finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa2.getDescricao());
			stmt.setBoolean(2, tarefa2.isFinalizado());
			stmt.setDate(3, tarefa2.getDataFinalizacao() != null ? 
					new Date(tarefa2.getDataFinalizacao().getTimeInMillis()) : null);
			stmt.setLong(4, tarefa2.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Tarefa2 tarefa2) {

		if (tarefa2.getId() == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		String sql = "delete from tarefas where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa2.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}