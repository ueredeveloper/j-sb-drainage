package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demanda")
public class DemandaModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Double demId;
	@Column(nullable = true, unique = false)
	private Double demVazao; // litros/dia
	@Column(nullable = true, unique = false)
	private int demTempo; // horas/dia
	@Column(nullable = true, unique = false)
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
