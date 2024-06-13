package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subterranea")
public class SubterraneaModel extends InterferenciaModel {

	private static final long serialVersionUID = 1L;

	@Column ()
	private Boolean subCaesb;  // tem caesb () sim () não

	@Column (columnDefinition="varchar(20)")
	private String subNivelEstatico;  // em metros

	@Column (columnDefinition="varchar(20)")
	private String subDinamico;  // em metros

	public Boolean getSubCaesb() {
		return subCaesb;
	}

	public void setSubCaesb(Boolean subCaesb) {
		this.subCaesb = subCaesb;
	}

	public String getSubNivelEstatico() {
		return subNivelEstatico;
	}

	public void setSubNivelEstatico(String subNivelEstatico) {
		this.subNivelEstatico = subNivelEstatico;
	}

	public String getSubDinamico() {
		return subDinamico;
	}

	public void setSubDinamico(String subDinamico) {
		this.subDinamico = subDinamico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
