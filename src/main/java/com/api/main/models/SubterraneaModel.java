package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "subterranea")
public class SubterraneaModel extends InterferenciaModel {
	
	private static final long serialVersionUID = 1L;

	@Column()
	private Boolean caesb; // tem caesb () sim () não

	@Column(columnDefinition = "varchar(20)")
	private String nivelEstatico; // em metros

	@Column(columnDefinition = "varchar(20)")
	private String nivelDinamico; // em metros

	public SubterraneaModel() {
		super();
	}

	public SubterraneaModel(Double latitude, Double longitude, Boolean caesb, String nivelEstatico,
			String nivelDinamico) {
		super(latitude, longitude);
		this.caesb = caesb;
		this.nivelEstatico = nivelEstatico;
		this.nivelDinamico = nivelDinamico;
	}
	

	public SubterraneaModel(Double latitude, Double longitude, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super(latitude, longitude, endereco, tipoInterferencia);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaModel(Double latitude, Double longitude, EnderecoModel endereco) {
		super(latitude, longitude, endereco);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaModel(Double latitude, Double longitude, TipoInterferenciaModel tipoInterferencia) {
		super(latitude, longitude, tipoInterferencia);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaModel(Double latitude, Double longitude) {
		super(latitude, longitude);
		// TODO Auto-generated constructor stub
	}
	
	public SubterraneaModel(Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super(latitude, longitude, geometry, endereco, tipoInterferencia, tipoOutorga, subtipoOutorga, situacaoProcesso, tipoAto);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaModel(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super(id, latitude, longitude, geometry, endereco, tipoInterferencia, tipoOutorga, subtipoOutorga, situacaoProcesso, tipoAto);
		// TODO Auto-generated constructor stub
	}

	public Boolean getCaesb() {
		return caesb;
	}

	public void setCaesb(Boolean caesb) {
		this.caesb = caesb;
	}

	public String getNivelEstatico() {
		return nivelEstatico;
	}

	public void setNivelEstatico(String nivelEstatico) {
		this.nivelEstatico = nivelEstatico;
	}

	public String getNivelDinamico() {
		return nivelDinamico;
	}

	public void setNivelDinamico(String nivelDinamico) {
		this.nivelDinamico = nivelDinamico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
