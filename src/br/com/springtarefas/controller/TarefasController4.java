package br.com.springtarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springtarefas.dao.TarefaDAO4;
import br.com.springtarefas.modelo.Tarefa4;

@Controller
public class TarefasController4{
	private TarefaDAO4 dao;
	
	@Autowired
	public TarefasController4(TarefaDAO4 dao) {
		this.dao = dao;
	}

	@RequestMapping("listaTarefas4")
	public String lista(Model model) {
		List<Tarefa4> tarefas4 = dao.lista();
		model.addAttribute("tarefas", tarefas4);
		return "tarefa/lista4";
	}

	@RequestMapping("alteraTarefa4")
	public String altera(Tarefa4 tarefa4){
		dao.altera(tarefa4);
		return "redirect:listaTarefas4";
	}
	
	@RequestMapping("mostraTarefa4")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra4";
	}

	@RequestMapping("removeTarefa4")
	public String remove(Tarefa4 tarefa4) {
		dao.remove(tarefa4);
		return "redirect:listaTarefas4";
	}
	
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/finalizada";
	}
}
