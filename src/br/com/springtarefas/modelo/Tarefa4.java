package br.com.springtarefas.modelo;

import java.time.LocalDate;

public class Tarefa4 {

	private Long id;
	private String descricao;
	private boolean finalizado;
	private LocalDate dataFinalizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}
	
	public void setDataFinalizacao(String dataFinalizacao) {
		this.dataFinalizacao = LocalDate.parse(dataFinalizacao);
	}

	@Override
	public String toString() {
		return "Tarefa3 [id=" + id + ", descricao=" + descricao + ", finalizado=" + finalizado + ", dataFinalizacao="
				+ dataFinalizacao + "]";
	}

	
}
