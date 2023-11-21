package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "processo")
public class ProcessoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long procId;
	@Column(nullable = true, unique = false, length = 40)
	String procNumero;

	@JsonIgnore
	@OneToMany(mappedBy = "anPrincipal")
	private List<AnexoModel> procProcessos = new ArrayList<>();
	
	
	public ProcessoModel() {
		super();
	}

	public ProcessoModel(Long procId, String procNumero, List<AnexoModel> procProcessos) {
		super();
		this.procId = procId;
		this.procNumero = procNumero;
		this.procProcessos = procProcessos;
	}

	public ProcessoModel(String procNumero) {
		super();
		this.procNumero = procNumero;
	}

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

	public List<AnexoModel> getProcProcessos() {
		return procProcessos;
	}

	public void setProcProcessos(List<AnexoModel> procProcessos) {
		this.procProcessos = procProcessos;
	}

}
