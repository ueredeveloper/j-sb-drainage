package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@JsonIgnore
	@OneToMany(mappedBy = "baciaHidrografica", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public BaciaHidrograficaModel() {
		super();
	}

}
