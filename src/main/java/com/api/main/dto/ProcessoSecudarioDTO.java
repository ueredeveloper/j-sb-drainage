package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;

public class ProcessoSecudarioDTO {

	private long procId;
	// @NotBlank
	private String procNumero;

	private ProcessoModel procPrincipal;

	private List<DocumentoModel> procDocumentos;

	
	public long getProcId() {
		return procId;
	}

	public void setProcId(long procId) {
		this.procId = procId;
	}

	public String getProcNumero() {
		return procNumero;
	}

	public void setProcNumero(String procNumero) {
		this.procNumero = procNumero;
	}

	public ProcessoModel getProcPrincipal() {
		return procPrincipal;
	}

	public void setProcPrincipal(ProcessoModel procPrincipal) {
		this.procPrincipal = procPrincipal;
	}

	public List<DocumentoModel> getProcDocumentos() {
		return procDocumentos;
	}

	public void setProcDocumentos(List<DocumentoModel> procDocumentos) {
		this.procDocumentos = procDocumentos;
	}

}
