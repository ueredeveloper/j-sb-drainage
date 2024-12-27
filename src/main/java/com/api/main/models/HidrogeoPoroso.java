package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "hidrogeo_poroso")
public class HidrogeoPoroso {

	@Id
	@Column(name = "objectid")
	private Long objectId;

	@Column(name = "uh_nome")
	private String uhNome;

	@Column(name = "uh_codigo")
	private String uhCodigo;

	@Column(name = "bacia_nome")
	private String baciaNome;

	@Column(name = "uh_label")
	private String uhLabel;

	@Column(name = "sistema")
	private String sistema;

	@Column(name = "q_media")
	private Double qMedia;

	@Column(name = "area_sq_m")
	private Double areaSqM;

	@Column(name = "b_m")
	private Double bM;

	@Column(name = "ne")
	private Double ne;

	@Column(name = "rp")
	private Double rp;

	@Column(name = "re")
	private Double re;

	@Column(name = "rr")
	private Double rr;

	@Column(name = "re_cm_ano")
	private Double reCmAno;

	@Column(name = "cod_plan")
	private String codPlan;

	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomAttrData;

	public HidrogeoPoroso() {
		super();
	}

}
