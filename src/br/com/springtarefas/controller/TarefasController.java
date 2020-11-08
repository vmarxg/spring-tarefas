package br.com.springtarefas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springtarefas.dao.TarefaDAO;
import br.com.springtarefas.modelo.Tarefa;

@Controller
public class TarefasController {

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	//Mágica! Nem precisamos fazer nada para criar o objeto tarefa. 
	//O framework já faz isso para nós. Agora é só mandar persistir no banco.
	@RequestMapping("adicionaTarefa")
	public String adiciona(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
	
	//Mágica! Para mandar a lista de objetos tarefas basta utiliza Model and View como parâmetro. 
	//O framework já faz isso para nós. Agora é só mandar persistir no banco.
	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		TarefaDAO dao = new TarefaDAO();
		List<Tarefa> tarefas = dao.lista();
		model.addAttribute("tarefas", tarefas);
		return "tarefa/lista";
	}
	
}
