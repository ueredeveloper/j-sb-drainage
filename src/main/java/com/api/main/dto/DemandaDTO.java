package com.api.main.dto;

public class DemandaDTO {

	
	
	private Double id;
	private Double vazao; // litros/dia
	private int tempo; // horas/dia
	private int periodo; // dias/mês
	
	public DemandaDTO() {
		super();
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public Double getVazao() {
		return vazao;
	}

	public void setVazao(Double vazao) {
		this.vazao = vazao;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	
}
