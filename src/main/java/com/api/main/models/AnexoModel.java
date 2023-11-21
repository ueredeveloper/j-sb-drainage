package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anexo")
public class AnexoModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long anId;

	@Column(nullable = true, unique = false, length = 40)
	private String anNumero;

	@ManyToOne
	@JoinColumn(name = "anPrincipal", referencedColumnName = "procId")
	private ProcessoModel anPrincipal;

	public AnexoModel() {
		super();
	}
	public AnexoModel (String anNumero) {
		super();
		this.anNumero = anNumero;
	}

	public Long getAnId() {
		return anId;
	}

	public void setAnId(Long anId) {
		this.anId = anId;
	}

	public String getAnNumero() {
		return anNumero;
	}

	public void setAnNumero(String anNumero) {
		this.anNumero = anNumero;
	}

	public ProcessoModel getAnPrincipal() {
		return anPrincipal;
	}

	public void setAnPrincipal(ProcessoModel anPrincipal) {
		this.anPrincipal = anPrincipal;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
