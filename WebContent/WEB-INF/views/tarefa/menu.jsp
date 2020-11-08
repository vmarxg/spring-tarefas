<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projeto fj21-tarefas</title>
</head>
<body>
	<h2>Página inicial da Lista de Tarefas</h2>
	<p>Bem vindo, ${usuarioLogado.login}</p>
	<!-- Na hora testar alterar de acordo com o necessario. Exemplo: listaTarefa3, listaTarefa4, listaTarefa5 -->
	<a href="listaTarefas5">Clique aqui</a> para acessar a lista de tarefas<br/>
	<a href="logout"><button>Sair</button></a>
</body>

</html>