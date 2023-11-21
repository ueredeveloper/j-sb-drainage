package com.api.main.dto;

import com.api.main.models.DocumentoTipoModel;
import com.api.main.models.ProcessoModel;
import com.api.main.models.AnexoModel;

//import javax.validation.constraints.NotBlank;
//@JsonSerialize(using = CustomDocTipoSerializer.class)
public class DocumentoDTO {
	

	private Long docId;
	// @NotBlank
	private String docNumero;
	// @NotBlank
	private ProcessoModel docProcesso;

	// @NotBlank
	private String docSEI;

	// @JsonInclude(Include.NON_NULL)
	private DocumentoTipoModel docTipo;

	public DocumentoDTO(String docNumero, ProcessoModel docProcesso, String docSEI, DocumentoTipoModel docTipo) {
		super();
		this.docNumero = docNumero;
		this.docProcesso = docProcesso;
		this.docSEI = docSEI;
		this.docTipo = docTipo;
	}
	
	public DocumentoDTO(String docNumero) {
		super();
		this.docNumero = docNumero;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocNumero() {
		return docNumero;
	}

	public void setDocNumero(String docNumero) {
		this.docNumero = docNumero;
	}

	public ProcessoModel getDocProcesso() {
		return docProcesso;
	}

	public void setDocProcesso(ProcessoModel docProcesso) {
		this.docProcesso = docProcesso;
	}

	public String getDocSEI() {
		return docSEI;
	}

	public void setDocSEI(String docSEI) {
		this.docSEI = docSEI;
	}

	public DocumentoTipoModel getDocTipo() {
		return docTipo;
	}

	public void setDocTipo(DocumentoTipoModel docTipo) {
		this.docTipo = docTipo;
	}

}