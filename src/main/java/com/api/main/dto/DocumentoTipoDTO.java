package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;

//import javax.validation.constraints.NotBlank;

public class DocumentoTipoDTO {
	

	private Long dtId;

	private String dtDescricao;

	private List<DocumentoModel> dtDocumentos;

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

	public List<DocumentoModel> getDtDocumentos() {
		return dtDocumentos;
	}

	public void setDtDocumentos(List<DocumentoModel> dtDocumentos) {
		this.dtDocumentos = dtDocumentos;
	}


}