package com.api.main.dto;

import com.api.main.models.TipoPocoModel;

public class SubterraneaDTO extends InterferenciaDTO {

	private Boolean caesb; // tem caesb () sim () não

	private String nivelEstatico; // em metros

	private String nivelDinamico; // em metros

	private String profundidade; // em metros

	private Integer vazaoOutorgavel;

	private Integer vazaoSistema;

	private Integer vazaoTeste;
	
	private Integer vazaoAutorizada; // 

	private TipoPocoModel tipoPoco;

	public SubterraneaDTO() {
	}

	public Boolean getCaesb() {
		return caesb;
	}

	public void setCaesb(Boolean caesb) {
		this.caesb = caesb;
	}

	public String getNivelEstatico() {
		return nivelEstatico;
	}

	public void setNivelEstatico(String nivelEstatico) {
		this.nivelEstatico = nivelEstatico;
	}

	public String getNivelDinamico() {
		return nivelDinamico;
	}

	public void setNivelDinamico(String nivelDinamico) {
		this.nivelDinamico = nivelDinamico;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getVazaoOutorgavel() {
		return vazaoOutorgavel;
	}

	public void setVazaoOutorgavel(Integer vazaoOutorgavel) {
		this.vazaoOutorgavel = vazaoOutorgavel;
	}

	public Integer getVazaoSistema() {
		return vazaoSistema;
	}

	public void setVazaoSistema(Integer vazaoSistema) {
		this.vazaoSistema = vazaoSistema;
	}

	public Integer getVazaoTeste() {
		return vazaoTeste;
	}

	public void setVazaoTeste(Integer vazaoTeste) {
		this.vazaoTeste = vazaoTeste;
	}

	public TipoPocoModel getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(TipoPocoModel tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public Integer getVazaoAutorizada() {
		return vazaoAutorizada;
	}

	public void setVazaoAutorizada(Integer vazaoAutorizada) {
		this.vazaoAutorizada = vazaoAutorizada;
	}
	
}
