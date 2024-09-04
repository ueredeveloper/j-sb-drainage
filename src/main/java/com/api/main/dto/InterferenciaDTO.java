package com.api.main.dto;

import com.api.main.models.BaciaHidrograficaModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.models.UnidadeHidrograficaModel;
import com.vividsolutions.jts.geom.Geometry;

public class InterferenciaDTO {

	
	private Long id;

	private Double latitude;

	private Double longitude;

	private Geometry geometry;

	private EnderecoModel endereco;

	private TipoInterferenciaModel tipoInterferencia;

	private TipoOutorgaModel tipoOutorga;

	private SubtipoOutorgaModel subtipoOutorga;

	private SituacaoProcessoModel situacaoProcesso;

	private TipoAtoModel tipoAto;

	private BaciaHidrograficaModel baciaHidrografica;

	private UnidadeHidrograficaModel unidadeHidrografica;

	public InterferenciaDTO() {
		super();
	}

	public InterferenciaDTO(Double latitude, Double longitude, EnderecoModel endereco) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
	}

	public InterferenciaDTO(Double latitude, Double longitude, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
		this.tipoInterferencia = tipoInterferencia;
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

	public InterferenciaDTO(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.geometry = geometry;
		this.endereco = endereco;
		this.tipoInterferencia = tipoInterferencia;
		this.tipoOutorga = tipoOutorga;
		this.subtipoOutorga = subtipoOutorga;
		this.situacaoProcesso = situacaoProcesso;
		this.tipoAto = tipoAto;
	}

	public InterferenciaDTO(Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.geometry = geometry;
		this.endereco = endereco;
		this.tipoInterferencia = tipoInterferencia;
		this.tipoOutorga = tipoOutorga;
		this.subtipoOutorga = subtipoOutorga;
		this.situacaoProcesso = situacaoProcesso;
		this.tipoAto = tipoAto;
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

	public TipoOutorgaModel getTipoOutorga() {
		return tipoOutorga;
	}

	public void setTipoOutorga(TipoOutorgaModel tipoOutorga) {
		this.tipoOutorga = tipoOutorga;
	}

	public SubtipoOutorgaModel getSubtipoOutorga() {
		return subtipoOutorga;
	}

	public void setSubtipoOutorga(SubtipoOutorgaModel subtipoOutorga) {
		this.subtipoOutorga = subtipoOutorga;
	}

	public SituacaoProcessoModel getSituacaoProcesso() {
		return situacaoProcesso;
	}

	public void setSituacaoProcesso(SituacaoProcessoModel situacaoProcesso) {
		this.situacaoProcesso = situacaoProcesso;
	}

	public TipoAtoModel getTipoAto() {
		return tipoAto;
	}

	public void setTipoAto(TipoAtoModel tipoAto) {
		this.tipoAto = tipoAto;
	}

	public BaciaHidrograficaModel getBaciaHidrografica() {
		return baciaHidrografica;
	}

	public void setBaciaHidrografica(BaciaHidrograficaModel baciaHidrografica) {
		this.baciaHidrografica = baciaHidrografica;
	}

	public UnidadeHidrograficaModel getUnidadeHidrografica() {
		return unidadeHidrografica;
	}

	public void setUnidadeHidrografica(UnidadeHidrograficaModel unidadeHidrografica) {
		this.unidadeHidrografica = unidadeHidrografica;
	}

	
}
