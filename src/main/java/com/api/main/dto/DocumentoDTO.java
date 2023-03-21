package com.api.main.dto;

import com.api.main.models.TipoDocumentoModel;

//import javax.validation.constraints.NotBlank;

public class DocumentoDTO {
	private long doc_id;
	// @NotBlank
	private String doc_numeracao;
	// @NotBlank
	private String doc_processo;

	// @NotBlank
	private String doc_numeracao_sei;

	private TipoDocumentoModel doc_td_fk;

	public long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_numeracao() {
		return doc_numeracao;
	}

	public void setDoc_numeracao(String doc_numeracao) {
		this.doc_numeracao = doc_numeracao;
	}

	public String getDoc_numeracao_sei() {
		return doc_numeracao_sei;
	}

	public void setDoc_numeracao_sei(String doc_numeracao_sei) {
		this.doc_numeracao_sei = doc_numeracao_sei;
	}

	public String getDoc_processo() {
		return doc_processo;
	}

	public void setDoc_processo(String doc_processo) {
		this.doc_processo = doc_processo;
	}

	public TipoDocumentoModel getDoc_td_fk() {
		return doc_td_fk;
	}

	public void setDoc_td_fk(TipoDocumentoModel doc_td_fk) {
		this.doc_td_fk = doc_td_fk;
	}

}