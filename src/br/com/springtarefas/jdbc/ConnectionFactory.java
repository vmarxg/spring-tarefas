package br.com.springtarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados...");

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mariadb://localhost:3306/java_web?user=root&password=");
		} catch (SQLException e) {
			System.out.println("Erro na execução da SQL: " + e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Classe para conexão do banco não encontrada: " + e);
			throw new RuntimeException(e);
		}			
	}

}
