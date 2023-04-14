package com.api.main.dto;

import java.util.List;

import com.api.main.models.ProcessoModel;

public class ProcessoDTO {
	private long proc_id;
	// @NotBlank
	private String proc_numero;
	
	private ProcessoModel proc_principal_fk;
	
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

	public List<ProcessoModel> getProcessos() {
		return processos;
	}
	public void setProcessos(List<ProcessoModel> processos) {
		this.processos = processos;
	}
	public ProcessoModel getProc_principal_fk() {
		return proc_principal_fk;
	}
	public void setProc_principal_fk(ProcessoModel proc_principal_fk) {
		this.proc_principal_fk = proc_principal_fk;
	}

	
}
