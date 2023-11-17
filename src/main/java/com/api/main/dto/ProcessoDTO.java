package com.api.main.dto;

import java.util.List;

import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;

public class ProcessoDTO {

	private long proId;
	// @NotBlank
	private String procNumero;

	private ProcessoModel procPrincipal;

	private List<ProcessoModel> procProcessos;

	private List<DocumentoModel> procDocumentos;

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
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

	public List<ProcessoModel> getProcProcessos() {
		return procProcessos;
	}

	public void setProcProcessos(List<ProcessoModel> procProcessos) {
		this.procProcessos = procProcessos;
	}

	public List<DocumentoModel> getProcDocumentos() {
		return procDocumentos;
	}

	public void setProcDocumentos(List<DocumentoModel> procDocumentos) {
		this.procDocumentos = procDocumentos;
	}

}
