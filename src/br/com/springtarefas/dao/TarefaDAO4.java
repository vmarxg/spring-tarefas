package br.com.springtarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.springtarefas.modelo.Tarefa4;

@Repository
public class TarefaDAO4 {
	Connection connection;
	
	@Autowired
	public TarefaDAO4(DataSource datasource){
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(Tarefa4 tarefa4){
		String sql = "insert into tarefas (descricao) " +
					"values (?)";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, tarefa4.getDescricao());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new RuntimeException (e);
		}
	}
	
	public List<Tarefa4> lista() {
		try {			
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas");
			ResultSet rs = stmt.executeQuery();
			
			List<Tarefa4> tarefas4 = new ArrayList<Tarefa4>();
			while (rs.next()) {
				// adiciona a tarefa na lista
				tarefas4.add(populaTarefa(rs));
			}

			rs.close();
			stmt.close();

			return tarefas4;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Tarefa4 populaTarefa(ResultSet rs) throws SQLException {
		Tarefa4 tarefa4 = new Tarefa4();

		// popula o objeto tarefa
		tarefa4.setId(rs.getLong("id"));
		tarefa4.setDescricao(rs.getString("descricao"));
		tarefa4.setFinalizado(rs.getBoolean("finalizado"));

		// popula a data de finalizacao da tarefa, fazendo a conversao
		Date dataFinalizacao = rs.getDate("dataFinalizacao");
		if (dataFinalizacao != null) {			
			tarefa4.setDataFinalizacao(dataFinalizacao.toString());

		}
		return tarefa4;
	}
	
	public Tarefa4 buscaPorId(Long id) {

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

	public void altera(Tarefa4 tarefa4) {
		String sql = "update tarefas set descricao = ?, finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa4.getDescricao());
			stmt.setBoolean(2, tarefa4.isFinalizado());
			stmt.setDate(3, tarefa4.getDataFinalizacao() != null ? 
					Date.valueOf(tarefa4.getDataFinalizacao()) : null);
			stmt.setLong(4, tarefa4.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Tarefa4 tarefa4) {

		if (tarefa4.getId() == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		String sql = "delete from tarefas where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa4.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void finaliza(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		String sql = "update tarefas set finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}