package com.api.main.dto;

import com.api.main.models.ProcessoModel;

public class AnexoDTO {
	

	private long anId;
	// @NotBlank
	private String anNumero;

	private ProcessoModel anPrincipal;

	public long getAnId() {
		return anId;
	}

	public void setAnId(long anId) {
		this.anId = anId;
	}

	public String getAnNumero() {
		return anNumero;
	}

	public void setAnNumero(String anNumero) {
		this.anNumero = anNumero;
	}

	public ProcessoModel getAnPrincipal() {
		return anPrincipal;
	}

	public void setAnPrincipal(ProcessoModel anPrincipal) {
		this.anPrincipal = anPrincipal;
	}

}
