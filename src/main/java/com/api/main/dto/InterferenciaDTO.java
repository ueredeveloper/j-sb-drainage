package com.api.main.dto;

import com.api.main.models.EnderecoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.vividsolutions.jts.geom.Geometry;

public class InterferenciaDTO {

	private Long id;

	private Double latitude;

	private Double longitude;

	private Geometry geometry;

	private EnderecoModel endereco;

	private TipoInterferenciaModel tipoInterferencia;

	public InterferenciaDTO() {
		super();
	}


	public InterferenciaDTO(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.geometry = geometry;
		this.endereco = endereco;
		this.tipoInterferencia = tipoInterferencia;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public TipoInterferenciaModel getTipoInterferencia() {
		return tipoInterferencia;
	}

	public void setTipoInterferencia(TipoInterferenciaModel tipoInterferencia) {
		this.tipoInterferencia = tipoInterferencia;
	}

	

}
