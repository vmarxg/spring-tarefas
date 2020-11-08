package br.com.springtarefas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springtarefas.dao.TarefaDAO2;
import br.com.springtarefas.modelo.Tarefa2;

@Controller
public class TarefasController2{
	private TarefaDAO2 dao;
	
	public TarefasController2() {
		this.dao = new TarefaDAO2();
	}

	@RequestMapping("listaTarefas2")
	public String lista(Model model) {
		List<Tarefa2> tarefas2 = dao.lista();
		model.addAttribute("tarefas", tarefas2);
		return "tarefa/lista2";
	}

	@RequestMapping("alteraTarefa2")
	public String altera(Tarefa2 tarefa2){
		dao.altera(tarefa2);
		return "redirect:listaTarefas2";
	}
	
	@RequestMapping("mostraTarefa2")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra2";
	}

	@RequestMapping("removeTarefa2")
	public String remove(Tarefa2 tarefa2) {
		dao.remove(tarefa2);
		return "redirect:listaTarefas2";
	}
}
