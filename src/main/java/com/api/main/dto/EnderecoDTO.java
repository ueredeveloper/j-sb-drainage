package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;

public class EnderecoDTO {

	private Long endId;

	private String endLogradouro;
	
	private String endCidade;

	private String endCEP;

	private List<DocumentoModel> endDocumentos;
	
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

	public String getEndCEP() {
		return endCEP;
	}

	public void setEndCEP(String endCEP) {
		this.endCEP = endCEP;
	}

	public List<DocumentoModel> getEndDocumentos() {
		return endDocumentos;
	}

	public void setEndDocumentos(List<DocumentoModel> endDocumentos) {
		this.endDocumentos = endDocumentos;
	}
	
}
