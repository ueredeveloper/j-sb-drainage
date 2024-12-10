package com.api.main.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bacia_hidrografica")
public class BaciaHidrograficaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@Column(name = "int_shape", columnDefinition = "geometry(POLYGON, 4674)")
	private Geometry intShape;

	@JsonIgnore
	@OneToMany(mappedBy = "baciaHidrografica", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public BaciaHidrograficaModel() {
		super();
	}

}
