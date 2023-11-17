package com.api.main.dto;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.ProcessoModel;

//import javax.validation.constraints.NotBlank;
//@JsonSerialize(using = CustomDocTipoSerializer.class)
public class DocumentoDTO {

	private Long doc_id;
	// @NotBlank
	private String doc_numero;
	// @NotBlank
	private ProcessoModel docProcesso;

	// @NotBlank
	private String doc_sei;

	// @JsonInclude(Include.NON_NULL)
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

	public ProcessoModel getDocProcesso() {
		return docProcesso;
	}

	public void setDocProcesso(ProcessoModel docProcesso) {
		this.docProcesso = docProcesso;
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