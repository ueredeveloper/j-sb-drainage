package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;
import com.api.main.models.EstadoModel;
import com.api.main.models.InterferenciaModel;

public class EnderecoDTO {

	private Long endId;

	private String endLogradouro;

	private String endCidade;

	private String endCep;

	private String endBairro;

	private EstadoModel endEstado;

	private Set<DocumentoModel> endDocumentos = new HashSet<>();

	private Set<InterferenciaModel> endInterferencias = new HashSet<>();

	public EnderecoDTO() {
		super();
	}

	public EnderecoDTO(String endLogradouro) {
		super();
		this.endLogradouro = endLogradouro;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public Set<DocumentoModel> getEndDocumentos() {
		return endDocumentos;
	}

	public void setEndDocumentos(Set<DocumentoModel> endDocumentos) {
		this.endDocumentos = endDocumentos;
	}

	public Set<InterferenciaModel> getEndInterferencias() {
		return endInterferencias;
	}

	public void setEndInterferencias(Set<InterferenciaModel> endInterferencias) {
		this.endInterferencias = endInterferencias;
	}

	public EstadoModel getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(EstadoModel endEstado) {
		this.endEstado = endEstado;
	}

}
