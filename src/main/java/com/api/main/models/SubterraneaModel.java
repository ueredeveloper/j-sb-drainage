package com.api.main.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subterranea")
public class SubterraneaModel extends InterferenciaModel {

	private static final long serialVersionUID = 1L;

	private String subVazao;

	public String getSubVazao() {
		return subVazao;
	}

	public void setSubVazao(String subVazao) {
		this.subVazao = subVazao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
