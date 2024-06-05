package com.api.main.dto;

public class DemandaDTO {

	private Double demId;
	private Double demVazao; // litros/dia
	private int demTempo; // horas/dia
	private int demPeriodo; // dias/mês

	public Double getDemId() {
		return demId;
	}

	public void setDemId(Double demId) {
		this.demId = demId;
	}

	public Double getDemVazao() {
		return demVazao;
	}

	public void setDemVazao(Double demVazao) {
		this.demVazao = demVazao;
	}

	public int getDemTempo() {
		return demTempo;
	}

	public void setDemTempo(int demTempo) {
		this.demTempo = demTempo;
	}

	public int getDemPeriodo() {
		return demPeriodo;
	}

	public void setDemPeriodo(int demPeriodo) {
		this.demPeriodo = demPeriodo;
	}

}
