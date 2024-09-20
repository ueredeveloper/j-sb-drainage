package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoModel;

public class ProcessoDTO {
	// v1.12.2
	
	Long id;
	
	String numero;
	
	private AnexoModel anexo;
	
	private Set<DocumentoModel> documentos = new HashSet<>();
	
	
	public ProcessoDTO() {
		super();
	}
	
	public ProcessoDTO(Long id) {
		super();
		this.id = id;
	}

	public ProcessoDTO(Long id, String numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public ProcessoDTO(Long id, String numero, AnexoModel anexo) {
		super();
		this.id = id;
		this.numero = numero;
		this.anexo = anexo;
	}

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
