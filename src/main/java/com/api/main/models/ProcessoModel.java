package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "processos")
public class ProcessoModel {
	
	public ProcessoModel() {
		
	}
	
	public ProcessoModel (String proc_numero) {
		this.proc_numero = proc_numero;
	}
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long proc_id;
	
	@Column(nullable = true, unique = false, length = 40)
	private String proc_numero;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "proc_id", scope = ProcessoModel.class)
	@ManyToOne
	@JoinColumn(name = "proc_principal_fk")
	private ProcessoModel proc_principal_fk;
	
	@JsonIgnore
	@OneToMany(mappedBy = "proc_principal_fk")
	private List<ProcessoModel> processos =  new ArrayList<>();
	
	
	public Long getProc_id() {
		return proc_id;
	}

	public void setProc_id(Long proc_id) {
		this.proc_id = proc_id;
	}

	public String getProc_numero() {
		return proc_numero;
	}

	public void setProc_numero(String proc_numero) {
		this.proc_numero = proc_numero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public ProcessoModel getProc_principal_fk() {
		return proc_principal_fk;
	}

	public void setProc_principal_fk(ProcessoModel proc_principal_fk) {
		this.proc_principal_fk = proc_principal_fk;
	}

	public List<ProcessoModel> getProcessos() {
		return processos;
	}

	public void setProcessos(List<ProcessoModel> processos) {
		this.processos = processos;
	}

	
}
