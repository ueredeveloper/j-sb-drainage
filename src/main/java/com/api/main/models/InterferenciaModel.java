package com.api.main.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "interferencia")
@Inheritance(strategy = InheritanceType.JOINED)

public class InterferenciaModel implements Serializable {
	// v1.12.2
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, unique = false)
	private Double latitude;

	@Column(nullable = true, unique = false)
	private Double longitude;

	@Column(nullable = true, unique = false)
	private Geometry geometry;

	@ManyToOne
	@JoinColumn(name = "endereco")
	// @JsonBackReference
	private EnderecoModel endereco;

	@ManyToOne
	@JoinColumn(name = "tipoInterferencia")
	private TipoInterferenciaModel tipoInterferencia;

	@ManyToOne
	@JoinColumn(name = "tipoOutorga")
	private TipoOutorgaModel tipoOutorga;

	@ManyToOne
	@JoinColumn(name = "subtipoOutorga")
	private SubtipoOutorgaModel subtipoOutorga;

	@ManyToOne
	@JoinColumn(name = "situacaoProcesso")
	private SituacaoProcessoModel situacaoProcesso;

	@ManyToOne
	@JoinColumn(name = "tipoAto")
	private TipoAtoModel tipoAto;

	@ManyToOne
	@JoinColumn(name = "baciaHidrografica")
	private BaciaHidrograficaModel baciaHidrografica;

	@ManyToOne
	@JoinColumn(name = "unidadeHidrografica")
	private UnidadeHidrograficaModel unidadeHidrografica;

	@OneToMany(mappedBy = "interferencia", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FinalidadeModel> finalidades = new HashSet<>();

	@OneToMany(mappedBy = "interferencia", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DemandaModel> demandas = new HashSet<>();

	public InterferenciaModel() {
		super();
	}

	public InterferenciaModel(Long id) {
		super();
		this.id = id;
	}

	public InterferenciaModel(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public InterferenciaModel(Double latitude, Double longitude, EnderecoModel endereco) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
	}

	public InterferenciaModel(Double latitude, Double longitude, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
		this.tipoInterferencia = tipoInterferencia;
	}

	public InterferenciaModel(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
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

	public InterferenciaModel(Double latitude, Double longitude, TipoInterferenciaModel tipoInterferencia) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.tipoInterferencia = tipoInterferencia;
	}

	public InterferenciaModel(Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
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

	public InterferenciaModel(Double latitude, Double longitude, Set<FinalidadeModel> finalidades) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.finalidades = finalidades;
	}

	public InterferenciaModel(Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto, BaciaHidrograficaModel baciaHidrografica,
			UnidadeHidrograficaModel unidadeHidrografica, Set<FinalidadeModel> finalidades) {
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
		this.baciaHidrografica = baciaHidrografica;
		this.unidadeHidrografica = unidadeHidrografica;
		this.finalidades = finalidades;
	}

	public InterferenciaModel(Long id, Double latitude, Double longitude, Geometry geometry, EnderecoModel endereco,
			TipoInterferenciaModel tipoInterferencia, TipoOutorgaModel tipoOutorga, SubtipoOutorgaModel subtipoOutorga,
			SituacaoProcessoModel situacaoProcesso, TipoAtoModel tipoAto, BaciaHidrograficaModel baciaHidrografica,
			UnidadeHidrograficaModel unidadeHidrografica, Set<FinalidadeModel> finalidades,
			Set<DemandaModel> demandas) {
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
		this.baciaHidrografica = baciaHidrografica;
		this.unidadeHidrografica = unidadeHidrografica;
		this.finalidades = finalidades;
		this.demandas = demandas;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoInterferenciaModel getTipoInterferencia() {
		return tipoInterferencia;
	}

	public void setTipoInterferencia(TipoInterferenciaModel tipoInterferencia) {
		this.tipoInterferencia = tipoInterferencia;
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
