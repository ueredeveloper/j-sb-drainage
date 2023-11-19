package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.main.models.ProcessoModel;

public class ProcessoPrincipalDTO {
	
	Long procId;
	String procNumero;
	private List<ProcessoModel> procProcessos = new ArrayList<>();

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

	public List<ProcessoModel> getProcProcessos() {
		return procProcessos;
	}

	public void setProcProcessos(List<ProcessoModel> procProcessos) {
		this.procProcessos = procProcessos;
	}
	

}
