package com.api.main.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.main.models.ProcessoModel;

public class AnexoDTO {

	
	private Long id;
	private String numero;
	private List<ProcessoModel> processos = new ArrayList<>();

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

	public List<ProcessoModel> getProcessos() {
		return processos;
	}

	public void setProcessos(List<ProcessoModel> processos) {
		this.processos = processos;
	}

}
