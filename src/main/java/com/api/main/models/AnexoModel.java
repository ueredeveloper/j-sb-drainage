package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "anexo")
public class AnexoModel {
	
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false, length = 40)
	private String numero;

	@OneToMany(mappedBy = "anexo", cascade = CascadeType.ALL, orphanRemoval = true)
	// @JsonBackReference
	private Set<ProcessoModel> processos = new HashSet<>();

	public AnexoModel() {
		super();
	}
	
	public AnexoModel(String numero) {
		super();
		this.numero = numero;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
