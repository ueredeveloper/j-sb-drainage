package com.api.main.dto;

public class SubterraneaDTO extends InterferenciaDTO {

	private Boolean caesb; // tem caesb () sim () não

	private String nivelEstatico; // em metros

	private String nivelDinamico; // em metros

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


}
