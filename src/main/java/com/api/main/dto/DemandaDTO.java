package com.api.main.dto;

import com.api.main.models.InterferenciaModel;
import com.api.main.models.TipoFinalidadeModel;


public class DemandaDTO {

	private Long id;
	private Double vazao; // litros/dia
	private int tempo; // horas/dia
	private int periodo; // dias/mês
	private int mes; // mês.

	private TipoFinalidadeModel tipoFinalidade;

	private InterferenciaModel interferencia;

	public DemandaDTO() {
		super();
	}

	public DemandaDTO(Long id, Double vazao, int tempo, int periodo, int mes) {
		super();
		this.id = id;
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
	}

	public DemandaDTO(Double vazao, int tempo, int periodo, int mes) {
		super();
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
	}

	public DemandaDTO(Long id, Double vazao, int tempo, int periodo, int mes, TipoFinalidadeModel tipoFinalidade,
			InterferenciaModel interferencia) {
		super();
		this.id = id;
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.tipoFinalidade = tipoFinalidade;
		this.interferencia = interferencia;
	}

	public DemandaDTO(Double vazao, int tempo, int periodo, int mes, TipoFinalidadeModel tipoFinalidade,
			InterferenciaModel interferencia) {
		super();
		this.vazao = vazao;
		this.tempo = tempo;
		this.periodo = periodo;
		this.mes = mes;
		this.tipoFinalidade = tipoFinalidade;
		this.interferencia = interferencia;
	}
	
	public DemandaDTO(Long id, Double vazao, int tempo, int periodo, int mes, TipoFinalidadeModel tipoFinalidade) {
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
