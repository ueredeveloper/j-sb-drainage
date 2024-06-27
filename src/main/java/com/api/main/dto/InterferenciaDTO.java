package com.api.main.dto;

import com.api.main.models.EnderecoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.vividsolutions.jts.geom.Geometry;

public class InterferenciaDTO {

	private Double interLatitude;

	private Double interLongitude;

	private Geometry intGeometry;

	private EnderecoModel interEndereco;

	private String interLogradouro;

	private TipoInterferenciaModel tipoInterferencia;

	public InterferenciaDTO() {
		super();
	}

	public InterferenciaDTO(Double interLatitude, Double interLongitude, String interLogradouro) {
		super();
		this.interLatitude = interLatitude;
		this.interLongitude = interLongitude;
		this.interLogradouro = interLogradouro;
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

	public Geometry getIntGeometry() {
		return intGeometry;
	}

	public void setIntGeometry(Geometry intGeometry) {
		this.intGeometry = intGeometry;
	}

	public EnderecoModel getInterEndereco() {
		return interEndereco;
	}

	public void setInterEndereco(EnderecoModel interEndereco) {
		this.interEndereco = interEndereco;
	}

	public String getInterLogradouro() {
		return interLogradouro;
	}

	public void setInterLogradouro(String interLogradouro) {
		this.interLogradouro = interLogradouro;
	}

	public TipoInterferenciaModel getTipoInterferencia() {
		return tipoInterferencia;
	}

	public void setTipoInterferencia(TipoInterferenciaModel tipoInterferencia) {
		this.tipoInterferencia = tipoInterferencia;
	}

}
