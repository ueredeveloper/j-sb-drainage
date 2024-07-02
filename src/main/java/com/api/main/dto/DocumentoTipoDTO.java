package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.DocumentoModel;

//import javax.validation.constraints.NotBlank;

public class DocumentoTipoDTO {

	private Long dtId;

	private String dtDescricao;

	private Set<DocumentoModel> dtDocumentos = new HashSet<>();

	public Long getDtId() {
		return dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	public String getDtDescricao() {
		return dtDescricao;
	}

	public void setDtDescricao(String dtDescricao) {
		this.dtDescricao = dtDescricao;
	}

	public Set<DocumentoModel> getDtDocumentos() {
		return dtDocumentos;
	}

	public void setDtDocumentos(Set<DocumentoModel> dtDocumentos) {
		this.dtDocumentos = dtDocumentos;
	}

}