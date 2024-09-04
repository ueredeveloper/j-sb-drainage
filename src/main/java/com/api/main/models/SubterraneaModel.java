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
	private Boolean subCaesb; // tem caesb () sim () não

	@Column(columnDefinition = "varchar(20)")
	private String subNivelEstatico; // em metros

	@Column(columnDefinition = "varchar(20)")
	private String subDinamico; // em metros

	public SubterraneaModel() {
		super();
	}

	public SubterraneaModel(Double latitude, Double longitude, Boolean subCaesb, String subNivelEstatico,
			String subDinamico) {
		super(latitude, longitude);
		this.subCaesb = subCaesb;
		this.subNivelEstatico = subNivelEstatico;
		this.subDinamico = subDinamico;
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
