package com.api.main.dto;

import com.api.main.models.EnderecoModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.vividsolutions.jts.geom.Geometry;

public class SubterraneaDTO extends InterferenciaDTO {

	// v1.12.2
	private Boolean caesb; // tem caesb () sim () não

	private String nivelEstatico; // em metros

	private String nivelDinamico; // em metros

	public SubterraneaDTO() {
	}

	public SubterraneaDTO(Double latitude, Double longitude, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super(latitude, longitude, endereco, tipoInterferencia);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaDTO(Double latitude, Double longitude, EnderecoModel endereco) {
		super(latitude, longitude, endereco);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaDTO(Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super(latitude, longitude, geometry, endereco, tipoInterferencia, tipoOutorga, subtipoOutorga, situacaoProcesso,
				tipoAto);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaDTO(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super(id, latitude, longitude, geometry, endereco, tipoInterferencia, tipoOutorga, subtipoOutorga,
				situacaoProcesso, tipoAto);
		// TODO Auto-generated constructor stub
	}

	public SubterraneaDTO(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super(id, latitude, longitude, geometry, endereco, tipoInterferencia);
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
	

}
