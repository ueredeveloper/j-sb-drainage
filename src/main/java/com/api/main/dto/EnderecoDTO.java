package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;
import com.api.main.models.InterferenciaModel;

public class EnderecoDTO {

	private Long endId;

	private String endLogradouro;

	private String endCidade;

	private String endCep;
	
	private String endBairro;

	private String endEstado;

	private List<DocumentoModel> endDocumentos;
	
	private List<InterferenciaModel> endInterferencias;

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

	public String getEndEstado() {
		return endEstado;
	}

	public void setEndEstado(String endEstado) {
		this.endEstado = endEstado;
	}

	public List<DocumentoModel> getEndDocumentos() {
		return endDocumentos;
	}

	public void setEndDocumentos(List<DocumentoModel> endDocumentos) {
		this.endDocumentos = endDocumentos;
	}

	public List<InterferenciaModel> getEndInterferencias() {
		return endInterferencias;
	}

	public void setEndInterferencias(List<InterferenciaModel> endInterferencias) {
		this.endInterferencias = endInterferencias;
	}

}
