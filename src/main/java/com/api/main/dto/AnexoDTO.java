package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import com.api.main.models.ProcessoModel;

public class AnexoDTO {

	private Long id;
	private String numero;
	private Set<ProcessoModel> processos = new HashSet<>();

	public AnexoDTO() {
		super();
	}

	public AnexoDTO(Long id) {
		super();
		this.id = id;
	}

	public AnexoDTO(Long id, String numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public AnexoDTO(String numero) {
		super();
		this.numero = numero;
	}

	public AnexoDTO(Long id, String numero, Set<ProcessoModel> processos) {
		super();
		this.id = id;
		this.numero = numero;
		this.processos = processos;
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

	public Set<ProcessoModel> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<ProcessoModel> processos) {
		this.processos = processos;
	}

}
