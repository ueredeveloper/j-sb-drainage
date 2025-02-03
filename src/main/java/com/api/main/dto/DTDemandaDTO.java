package com.api.main.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class DTDemandaDTO {

	private Long ind_id;
	private Long end_id;
	private String end_logradouro;
	private Double int_latitude;
	private Double int_longitude;
	private JsonNode dt_demanda; // Ajuste aqui!
	private Long sub_tp_id;

	private Double vol_anual_ma;

	public DTDemandaDTO() {
		super();
	}

	public Long getInd_id() {
		return ind_id;
	}

	public void setInd_id(Long ind_id) {
		this.ind_id = ind_id;
	}

	public Long getEnd_id() {
		return end_id;
	}

	public void setEnd_id(Long end_id) {
		this.end_id = end_id;
	}

	public String getEnd_logradouro() {
		return end_logradouro;
	}

	public void setEnd_logradouro(String end_logradouro) {
		this.end_logradouro = end_logradouro;
	}

	public Double getInt_latitude() {
		return int_latitude;
	}

	public void setInt_latitude(Double int_latitude) {
		this.int_latitude = int_latitude;
	}

	public Double getInt_longitude() {
		return int_longitude;
	}

	public void setInt_longitude(Double int_longitude) {
		this.int_longitude = int_longitude;
	}

	public JsonNode getDt_demanda() {
		return dt_demanda;
	}

	public void setDt_demanda(JsonNode dt_demanda) {
		this.dt_demanda = dt_demanda;
	}

	public Long getSub_tp_id() {
		return sub_tp_id;
	}

	public void setSub_tp_id(Long sub_tp_id) {
		this.sub_tp_id = sub_tp_id;
	}

	public Double getVol_anual_ma() {
		return vol_anual_ma;
	}

	public void setVol_anual_ma(Double vol_anual_ma) {
		this.vol_anual_ma = vol_anual_ma;
	}

}
