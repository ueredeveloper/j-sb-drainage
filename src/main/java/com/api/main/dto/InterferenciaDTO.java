package com.api.main.dto;

import java.util.HashSet;
import java.util.Set;

import org.locationtech.jts.geom.Geometry;

import com.api.main.models.BaciaHidrograficaModel;
import com.api.main.models.DemandaModel;
import com.api.main.models.EnderecoModel;
import com.api.main.models.FinalidadeModel;
import com.api.main.models.SituacaoProcessoModel;
import com.api.main.models.SubtipoOutorgaModel;
import com.api.main.models.TipoAtoModel;
import com.api.main.models.TipoInterferenciaModel;
import com.api.main.models.TipoOutorgaModel;
import com.api.main.models.UnidadeHidrograficaModel;


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

	private Set<FinalidadeModel> finalidades = new HashSet<>();

	private Set<DemandaModel> demandas = new HashSet<>();

	public InterferenciaDTO() {
		super();
	}
	
	public InterferenciaDTO(Long id) {
		super();
		this.id = id;
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

	public Set<FinalidadeModel> getFinalidades() {
		return finalidades;
	}

	public void setFinalidades(Set<FinalidadeModel> finalidades) {
		this.finalidades = finalidades;
	}

	public Set<DemandaModel> getDemandas() {
		return demandas;
	}

	public void setDemandas(Set<DemandaModel> demandas) {
		this.demandas = demandas;
	}

}
