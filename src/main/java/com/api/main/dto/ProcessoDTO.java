package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.main.models.AnexoModel;
import com.api.main.models.DocumentoModel;

public class ProcessoDTO {

	
	Long procId;

	String procNumero;

	private AnexoModel anexo;

	private List<DocumentoModel> anDocumentos = new ArrayList<>();

	public Long getProcId() {
		return procId;
	}

	public void setProcId(Long procId) {
		this.procId = procId;
	}

	public String getProcNumero() {
		return procNumero;
	}

	public void setProcNumero(String procNumero) {
		this.procNumero = procNumero;
	}

	public AnexoModel getAnexo() {
		return anexo;
	}

	public void setAnexo(AnexoModel anexo) {
		this.anexo = anexo;
	}

	public List<DocumentoModel> getAnDocumentos() {
		return anDocumentos;
	}

	public void setAnDocumentos(List<DocumentoModel> anDocumentos) {
		this.anDocumentos = anDocumentos;
	}

	

}
