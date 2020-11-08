package br.com.springtarefas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springtarefas.dao.TarefaDAO3;
import br.com.springtarefas.modelo.Tarefa3;

@Controller
public class TarefasController3{
	private TarefaDAO3 dao;
	
	public TarefasController3() {
		this.dao = new TarefaDAO3();
	}

	@RequestMapping("listaTarefas3")
	public String lista(Model model) {
		List<Tarefa3> tarefas3 = dao.lista();
//		for(Tarefa3 c : tarefas3) {
//			  System.out.println(c.toString());
//			}
		model.addAttribute("tarefas", tarefas3);
//		System.out.println(model.toString());
		return "tarefa/lista3";
	}

	@RequestMapping("alteraTarefa3")
	public String altera(Tarefa3 tarefa3){
//		System.out.println(tarefa3.toString());
//		return null;
		dao.altera(tarefa3);
		return "redirect:listaTarefas3";
	}
	
	@RequestMapping("mostraTarefa3")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra3";
	}

	@RequestMapping("removeTarefa3")
	public String remove(Tarefa3 tarefa3) {
		dao.remove(tarefa3);
		return "redirect:listaTarefas3";
	}
}
