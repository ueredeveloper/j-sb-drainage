package com.api.main.dto;

import java.util.List;

import com.api.main.models.ProcessoModel;

public class ProcessoDTO {
	
	private long proc_id;
	// @NotBlank
	private String proc_numero;
	
	private ProcessoModel proc_processo_principal;
	
	private List<ProcessoModel> processos;

	public long getProc_id() {
		return proc_id;
	}

	public void setProc_id(long proc_id) {
		this.proc_id = proc_id;
	}

	public String getProc_numero() {
		return proc_numero;
	}

	public void setProc_numero(String proc_numero) {
		this.proc_numero = proc_numero;
	}

	public ProcessoModel getProc_processo_principal() {
		return proc_processo_principal;
	}

	public void setProc_processo_principal(ProcessoModel proc_processo_principal) {
		this.proc_processo_principal = proc_processo_principal;
	}

	public List<ProcessoModel> getProcessos() {
		return processos;
	}

	public void setProcessos(List<ProcessoModel> processos) {
		this.processos = processos;
	}

  
	
}
