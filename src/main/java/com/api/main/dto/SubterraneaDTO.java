package com.api.main.dto;

public class SubterraneaDTO {

	private Long interId;
	private Double interLatitude;
	private Double interLongitude;
	private String subVazao;

	public SubterraneaDTO() {
	}

	public Long getInterId() {
		return interId;
	}

	public void setInterId(Long interId) {
		this.interId = interId;
	}

	public Double getInterLatitude() {
		return interLatitude;
	}

	public void setInterLatitude(Double interLatitude) {
		this.interLatitude = interLatitude;
	}

	public Double getInterLongitude() {
		return interLongitude;
	}

	public void setInterLongitude(Double interLongitude) {
		this.interLongitude = interLongitude;
	}

	public String getSubVazao() {
		return subVazao;
	}

	public void setSubVazao(String subVazao) {
		this.subVazao = subVazao;
	}

}
