<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/js/jquery.js"></script>
<title>Projeto fj21-tarefas</title>
</head>
<body>

	<script type="text/javascript">
		function finalizaAgora(id) {
				$.post("finalizaTarefa", {'id' : id}, function(resposta) {
					// selecionando o elemento html através da
					// ID e alterando o HTML dele
					$("#tarefa_"+id).html(resposta);
			});
		}
	</script>

	<a href="menu"><button>Menu</button></a><br/>
	<a href="novaTarefa">Criar nova tarefa</a>
	<br />
	<br />
	<table>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr id="tarefa_${tarefa.id}">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
					<td>
						<a href="#" onClick="finalizaAgora(${tarefa.id})"> Finaliza agora! </a>
					</td>
				</c:if>
				<c:if test="${tarefa.finalizado eq true}">
					<td>Finalizado</td>
				</c:if>
				<td>
					<fmt:parseDate value="${tarefa.dataFinalizacao}" 
						type="date" pattern="yyyy-MM-dd" var="parsedDate" /> 
					<fmt:formatDate value="${parsedDate}" type="date" pattern="dd/MM/yyyy" />
				</td>
				<td><a href="mostraTarefa4?id=${tarefa.id}">Alterar</a></td>
				<td><a href="removeTarefa4?id=${tarefa.id}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>