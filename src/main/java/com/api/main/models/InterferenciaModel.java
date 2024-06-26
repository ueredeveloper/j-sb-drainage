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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "interferencia")
@Inheritance(strategy = InheritanceType.JOINED)
public class InterferenciaModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interId;

	@Column(nullable = true, unique = false)
	private Double interLatitude;

	@Column(nullable = true, unique = false)
	private Double interLongitude;

	@Column(nullable = true, unique = false)
	private Geometry interGeometry;
	
	@ManyToOne
	@JoinColumn(name = "interEndereco")
	private EnderecoModel interEndereco;
	
	@ManyToOne
	@JoinColumn(name = "tipoInterferencia", referencedColumnName = "id")
	@JsonBackReference
	private TipoInterferenciaModel tipoInterferencia;
	
	
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

	public Geometry getInterGeometry() {
		return interGeometry;
	}

	public void setInterGeometry(Geometry interGeometry) {
		this.interGeometry = interGeometry;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EnderecoModel getInterEndereco() {
		return interEndereco;
	}

	public void setInterEndereco(EnderecoModel interEndereco) {
		this.interEndereco = interEndereco;
	}

	public TipoInterferenciaModel getTipoInterferencia() {
		return tipoInterferencia;
	}

	public void setTipoInterferencia(TipoInterferenciaModel tipoInterferencia) {
		this.tipoInterferencia = tipoInterferencia;
	}
	
	
}
