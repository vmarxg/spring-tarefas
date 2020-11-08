package br.com.springtarefas.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tarefa2 {

	private Long id;
	private String descricao;
	private boolean finalizado;
	private Calendar dataFinalizacao; //Date e Calendar são API antigas, até o java7.

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

	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(String dataFinalizacao) {
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy")
			.parse(dataFinalizacao);
		} catch (ParseException e) {
			System.out.println("Erro de conversão da data");
			return; //para a execução do método
		}
		Calendar dataAux = Calendar.getInstance();
		dataAux.setTime(date);
		this.dataFinalizacao = dataAux;
	}

	@Override
	public String toString() {
		return "Tarefa2 [id=" + id + ", descricao=" + descricao + ", finalizado=" + finalizado + ", dataFinalizacao="
				+ dataFinalizacao + "]";
	}

}
