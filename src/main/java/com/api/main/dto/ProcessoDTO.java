package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoModel;

public class ProcessoDTO {

	
	Long id;
	String numero;
	private AnexoModel anexo;
	private Set<DocumentoModel> documentos = new HashSet<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public AnexoModel getAnexo() {
		return anexo;
	}
	public void setAnexo(AnexoModel anexo) {
		this.anexo = anexo;
	}
	public Set<DocumentoModel> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(Set<DocumentoModel> documentos) {
		this.documentos = documentos;
	}


}
