<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projeto fj21-tarefas</title>
</head>
<body>
	<h3>Alterar tarefa - ${tarefa.id}</h3>
	<form action="alteraTarefa4" method="post">
		<input type="hidden" name="id" value="${tarefa.id}" /> Descrição:<br />
		<textarea name="descricao" cols="100" rows="5">${tarefa.descricao}</textarea><br /> 
		Finalizado? <input type="checkbox" name="finalizado" value="true" ${tarefa.finalizado? 'checked' : '' } /> <br /> 
		Data de finalização: <br /> 
		<input type="date" name="dataFinalizacao" value="${tarefa.dataFinalizacao}"/> <br /> 
		<input type="submit" value="Alterar" />
	</form>
</body>
</html>