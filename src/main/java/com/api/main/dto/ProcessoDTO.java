package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.main.models.AnexoModel;

public class ProcessoDTO {
	
	
	Long procId;
	String procNumero;
	private List<AnexoModel> procAnexos = new ArrayList<>();
	
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
	public List<AnexoModel> getProcAnexos() {
		return procAnexos;
	}
	public void setProcAnexos(List<AnexoModel> procAnexos) {
		this.procAnexos = procAnexos;
	}

	
	

}
