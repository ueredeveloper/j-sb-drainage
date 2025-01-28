package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.locationtech.jts.geom.Geometry;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "bacias_hidrograficas")
public class BaciaHidrograficaModel {

	@Id
	@Column(name = "objectid")
	private Long objectid;

	@Column(name = "shape_leng")
	private Double shapeLeng;

	@Column(name = "bacia_cod")
	private Long baciaCod;

	@Column(name = "bacia_nome")
	private String baciaNome;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomattrData;
	// Adiciona transient para ao salvar não ter erro de conversão desta tabela para json
	//  could not deserialize] with root cause
	//java.io.StreamCorruptedException: invalid stream header: 30313033

	@Transient
	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@JsonIgnore
	@OneToMany(mappedBy = "baciaHidrografica", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public BaciaHidrograficaModel() {
		super();
	}
	
	

	public BaciaHidrograficaModel(Long objectid) {
		super();
		this.objectid = objectid;
	}



	public Long getObjectid() {
		return objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public Double getShapeLeng() {
		return shapeLeng;
	}

	public void setShapeLeng(Double shapeLeng) {
		this.shapeLeng = shapeLeng;
	}

	public Long getBaciaCod() {
		return baciaCod;
	}

	public void setBaciaCod(Long baciaCod) {
		this.baciaCod = baciaCod;
	}

	public String getBaciaNome() {
		return baciaNome;
	}

	public void setBaciaNome(String baciaNome) {
		this.baciaNome = baciaNome;
	}

	public String getGdbGeomattrData() {
		return gdbGeomattrData;
	}

	public void setGdbGeomattrData(String gdbGeomattrData) {
		this.gdbGeomattrData = gdbGeomattrData;
	}

	public Geometry getShape() {
		return shape;
	}

	public void setShape(Geometry shape) {
		this.shape = shape;
	}

	public Set<InterferenciaModel> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(Set<InterferenciaModel> interferencias) {
		this.interferencias = interferencias;
	}
	
	

}
