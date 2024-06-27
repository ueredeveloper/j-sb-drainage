package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.PrinterMoreInfo;

import com.api.main.models.InterferenciaModel;

public class TipoInterferenciaDTO {

	
	Long id;

	String descricao;

	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public TipoInterferenciaDTO() {
		super();
	}

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

	public Set<InterferenciaModel> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(Set<InterferenciaModel> interferencias) {
		this.interferencias = interferencias;
	}



}
