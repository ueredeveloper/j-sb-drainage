package com.api.main.dto;

import com.api.main.models.DocumentoTipoModel;

//import javax.validation.constraints.NotBlank;

public class DocumentoDTO {
	
	private Long doc_id;
	// @NotBlank
	private String doc_numero;
	// @NotBlank
	private String doc_processo;

	// @NotBlank
	private String doc_sei;

	private DocumentoTipoModel doc_tipo;

	public Long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_numero() {
		return doc_numero;
	}

	public void setDoc_numero(String doc_numero) {
		this.doc_numero = doc_numero;
	}

	public String getDoc_processo() {
		return doc_processo;
	}

	public void setDoc_processo(String doc_processo) {
		this.doc_processo = doc_processo;
	}

	public String getDoc_sei() {
		return doc_sei;
	}

	public void setDoc_sei(String doc_sei) {
		this.doc_sei = doc_sei;
	}

	public DocumentoTipoModel getDoc_tipo() {
		return doc_tipo;
	}

	public void setDoc_tipo(DocumentoTipoModel doc_tipo) {
		this.doc_tipo = doc_tipo;
	}


}