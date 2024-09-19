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
	private Double id;
	@Column(nullable = true, unique = false)
	private Double vazao; // litros/dia
	@Column(nullable = true, unique = false)
	private int tempo; // horas/dia
	@Column(nullable = true, unique = false)
	private int periodo; // dias/mês
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
