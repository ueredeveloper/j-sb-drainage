package com.api.main.dto;

public class DemandaPorEnderecoIdDTO {

	private Long intId;
	private Long endId;
	private String endLogradouro;
	private Double intLatitude;
	private Double intLongitude;
	private String subTpId;
	private String dtDemanda; // Store the raw JSON as a String
	private Double volAnualMa;

	public DemandaPorEnderecoIdDTO() {
		super();
	}

	public Long getIntId() {
		return intId;
	}

	public void setIntId(Long intId) {
		this.intId = intId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public String getEndLogradouro() {
		return endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) {
		this.endLogradouro = endLogradouro;
	}

	public Double getIntLatitude() {
		return intLatitude;
	}

	public void setIntLatitude(Double intLatitude) {
		this.intLatitude = intLatitude;
	}

	public Double getIntLongitude() {
		return intLongitude;
	}

	public void setIntLongitude(Double intLongitude) {
		this.intLongitude = intLongitude;
	}

	public String getSubTpId() {
		return subTpId;
	}

	public void setSubTpId(String subTpId) {
		this.subTpId = subTpId;
	}

	public String getDtDemanda() {
		return dtDemanda;
	}

	public void setDtDemanda(String dtDemanda) {
		this.dtDemanda = dtDemanda;
	}

	public Double getVolAnualMa() {
		return volAnualMa;
	}

	public void setVolAnualMa(Double volAnualMa) {
		this.volAnualMa = volAnualMa;
	}

}
