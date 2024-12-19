package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "hidrogeo_fraturado")
public class HidrogeoFraturado {

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

	@Column(name = "nome")
	private String nome;

	@Column(name = "hidrogeo")
	private String hidrogeo;

	@Column(name = "vazao")
	private Double vazao;

	@Column(name = "area_sq_m")
	private Double areaSqM;

	@Column(name = "sistema")
	private String sistema;

	@Column(name = "subsistema")
	private String subsistema;

	@Column(name = "ref")
	private String ref;

	@Column(name = "rr_cm_ano")
	private Double rrCmAno;

	@Column(name = "esp_raso")
	private Double espRaso;

	@Column(name = "ifr")
	private Double ifr;

	@Column(name = "rpr_cm_an")
	private Double rprCmAn;

	@Column(name = "esp_profun")
	private Double espProfund;

	@Column(name = "ifp")
	private Double ifp;

	@Column(name = "rpp_cm_an")
	private Double rppCmAn;

	@Column(name = "rp_cm_ano")
	private Double rpCmAno;

	@Column(name = "f_rpd")
	private Double fRpd;

	@Column(name = "rpd")
	private Double rpd;

	@Column(name = "re_cm_an")
	private Double reCmAn;

	@Column(name = "cod_plan")
	private String codPlan;

	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomattrData;

	public HidrogeoFraturado() {
		super();
	}

}
