package com.api.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "interferencia")
@Inheritance(strategy = InheritanceType.JOINED)
public class InterferenciaModel implements Serializable {

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
	@JoinColumn(name = "situacao")
	private SituacaoModel situacao;
	
	@ManyToOne
	@JoinColumn(name = "tipoAto")
	private TipoAtoModel tipoAto;
	
	@ManyToOne
	@JoinColumn(name = "baciaHidrografica")
	private BaciaHidrograficaModel baciaHidrografica;
	
	@ManyToOne
	@JoinColumn(name = "unidadeHidrografica")
	private UnidadeHidrograficaModel unidadeHidrografica;

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

	public SituacaoModel getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoModel situacao) {
		this.situacao = situacao;
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
	

}
