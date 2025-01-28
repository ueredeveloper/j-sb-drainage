package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "demanda")
public class DemandaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true, unique = false)
	private Double vazao; // litros/dia
	@Column(nullable = true, unique = false)
	private int tempo; // horas/dia
	@Column(nullable = true, unique = false)
	private int periodo; // dias/mês.
	@Column(nullable = true, unique = false)
	private int mes; // mês.
	
	@ManyToOne
	@JoinColumn(name = "tipoFinalidade", referencedColumnName = "id", nullable = false)
	private TipoFinalidadeModel tipoFinalidade;

	@ManyToOne
	@JoinColumn(name = "interferencia", referencedColumnName = "id", nullable = false)
	private InterferenciaModel interferencia;
	
	public DemandaModel() {
		super();
	}
	

	public DemandaModel(Long id, Double vazao, int tempo, int periodo, int mes, InterferenciaModel interferencia) {
		super();
		this.id = id;
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.interferencia = interferencia;
	}

	public DemandaModel(Double vazao, int tempo, int periodo, int mes, InterferenciaModel interferencia) {
		super();
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.interferencia = interferencia;
	}
	
	public DemandaModel(Long id, Double vazao, int tempo, int periodo, int mes) {
		super();
		this.id = id;
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
	}
	

	public DemandaModel(Double vazao, int tempo, int periodo, int mes, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.tipoFinalidade = tipoFinalidade;
	}
	
	public DemandaModel(Long id, Double vazao, int tempo, int periodo, int mes, TipoFinalidadeModel tipoFinalidade) {
		super();
		this.id = id;
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.tipoFinalidade = tipoFinalidade;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public InterferenciaModel getInterferencia() {
		return interferencia;
	}
	public void setInterferencia(InterferenciaModel interferencia) {
		this.interferencia = interferencia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public TipoFinalidadeModel getTipoFinalidade() {
		return tipoFinalidade;
	}


	public void setTipoFinalidade(TipoFinalidadeModel tipoFinalidade) {
		this.tipoFinalidade = tipoFinalidade;
	}
	

}
