package com.api.main.dto;

public class HidrogeoFraturadoDTO {

	private Long objectid;
	private String codPlan;
	private String sistema;
	private String subsistema;

	public Long getObjectId() {
		return objectid;
	}

	public void setObjectId(Long objectid) {
		this.objectid = objectid;
	}

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}

}
