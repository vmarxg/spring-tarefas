package br.com.springtarefas.modelo;

import java.time.LocalDate;

//Vamos trabalhar com nova API para datas.
//https://www.joda.org/joda-time/
public class Tarefa3 {

	private Long id;
	private String descricao;
	private boolean finalizado;
	private LocalDate dataFinalizacao; // API válida a partir do java 8.

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
//		this.dataFinalizacao = LocalDate.parse(dataFinalizacao, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		this.dataFinalizacao = dataFinalizacao;
//		date = new SimpleDateFormat("dd/MM/yyyy")
//		Calendar dataAux = Calendar.getInstance();
//		dataAux.setTime(date);
//		this.dataFinalizacao = date;
//		System.out.println(dataFinalizacao);
//		System.out.println("teste3");
//		System.out.println(dataFinalizacao);
//		System.out.println(this.dataFinalizacao);
//		System.exit(0);
//		this.dataFinalizacao = LocalDateTime.parse(dataFinalizacao);
	}

	@Override
	public String toString() {
		return "Tarefa3 [id=" + id + ", descricao=" + descricao + ", finalizado=" + finalizado + ", dataFinalizacao="
				+ dataFinalizacao + "]";
	}

	
}
