package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;

//import javax.validation.constraints.NotBlank;

public class TipoDocumentoDTO {

	private Long td_id;

	private String td_descricao;

	private List<DocumentoModel> documentos;

	public Long getTd_id() {
		return td_id;
	}

	public void setTd_id(Long td_id) {
		this.td_id = td_id;
	}

	public String getTd_descricao() {
		return td_descricao;
	}

	public void setTd_descricao(String td_descricao) {
		this.td_descricao = td_descricao;
	}

	public List<DocumentoModel> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoModel> documentos) {
		this.documentos = documentos;
	}

	
}