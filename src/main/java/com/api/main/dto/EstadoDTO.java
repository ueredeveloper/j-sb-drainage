package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.EnderecoModel;

public class EstadoDTO {

	Long id;
	String descricao;
	private Set<EnderecoModel> enderecos = new HashSet<>();

	public EstadoDTO() {
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

	public Set<EnderecoModel> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}

}
