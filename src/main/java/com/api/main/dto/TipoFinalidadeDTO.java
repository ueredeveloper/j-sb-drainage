package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.FinalidadeModel;

public class TipoFinalidadeDTO {
	// v1.12.2
	private Long id;

	private String descricao;

	private Set<FinalidadeModel> finalidades = new HashSet<>();

	public TipoFinalidadeDTO() {
		super();
	}

	public TipoFinalidadeDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
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

	public Set<FinalidadeModel> getFinalidades() {
		return finalidades;
	}

	public void setFinalidades(Set<FinalidadeModel> finalidades) {
		this.finalidades = finalidades;
	}

}