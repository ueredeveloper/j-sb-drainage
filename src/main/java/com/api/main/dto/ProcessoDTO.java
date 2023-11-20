package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.main.models.ProcessoSecudarioModel;

public class ProcessoDTO {
	
	Long procId;
	String procNumero;
	private List<ProcessoSecudarioModel> procProcessos = new ArrayList<>();

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

	public List<ProcessoSecudarioModel> getProcProcessos() {
		return procProcessos;
	}

	public void setProcProcessos(List<ProcessoSecudarioModel> procProcessos) {
		this.procProcessos = procProcessos;
	}
	

}
