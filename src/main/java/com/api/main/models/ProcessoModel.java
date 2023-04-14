package com.api.main.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private long proc_id;
	
	@Column(nullable = true, unique = false, length = 40)
	private String proc_numero;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proc_fk")
	private ProcessoModel proc_fk;
	
	@OneToMany(mappedBy = "proc_fk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProcessoModel> processos = new ArrayList<>();
	
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProcessoModel getProc_fk() {
		return proc_fk;
	}

	public void setProc_fk(ProcessoModel proc_fk) {
		this.proc_fk = proc_fk;
	}

	public List<ProcessoModel> getProcessos() {
		return processos;
	}

	public void setProcessos(List<ProcessoModel> processos) {
		this.processos = processos;
	}

	
	
	

}
