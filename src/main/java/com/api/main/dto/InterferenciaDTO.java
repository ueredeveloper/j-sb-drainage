package com.api.main.dto;

import com.api.main.models.EnderecoModel;
import com.api.main.models.InterferenciaTipoModel;
import com.vividsolutions.jts.geom.Geometry;

public class InterferenciaDTO {

	private Long interId;

	private Double interLatitude;

	private Double interLongitude;

	private Geometry intGeometry;

	private EnderecoModel interEndereco;

	private InterferenciaTipoModel interferenciaTipo;

	public InterferenciaDTO() {
		super();
	}

	public InterferenciaDTO(Double interLatitude, Double interLongitude, Geometry intGeometry,
			EnderecoModel interEndereco, InterferenciaTipoModel interferenciaTipo) {
		super();
		this.interLatitude = interLatitude;
		this.interLongitude = interLongitude;
		this.intGeometry = intGeometry;
		this.interEndereco = interEndereco;
		this.interferenciaTipo = interferenciaTipo;
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

	public InterferenciaTipoModel getInterferenciaTipo() {
		return interferenciaTipo;
	}

	public void setInterferenciaTipo(InterferenciaTipoModel interferenciaTipo) {
		this.interferenciaTipo = interferenciaTipo;
	}

}
