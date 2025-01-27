package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@Transient
	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomAttrData;

	public HidrogeoPoroso() {
		super();
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getUhNome() {
		return uhNome;
	}

	public void setUhNome(String uhNome) {
		this.uhNome = uhNome;
	}

	public String getUhCodigo() {
		return uhCodigo;
	}

	public void setUhCodigo(String uhCodigo) {
		this.uhCodigo = uhCodigo;
	}

	public String getBaciaNome() {
		return baciaNome;
	}

	public void setBaciaNome(String baciaNome) {
		this.baciaNome = baciaNome;
	}

	public String getUhLabel() {
		return uhLabel;
	}

	public void setUhLabel(String uhLabel) {
		this.uhLabel = uhLabel;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public Double getqMedia() {
		return qMedia;
	}

	public void setqMedia(Double qMedia) {
		this.qMedia = qMedia;
	}

	public Double getAreaSqM() {
		return areaSqM;
	}

	public void setAreaSqM(Double areaSqM) {
		this.areaSqM = areaSqM;
	}

	public Double getbM() {
		return bM;
	}

	public void setbM(Double bM) {
		this.bM = bM;
	}

	public Double getNe() {
		return ne;
	}

	public void setNe(Double ne) {
		this.ne = ne;
	}

	public Double getRp() {
		return rp;
	}

	public void setRp(Double rp) {
		this.rp = rp;
	}

	public Double getRe() {
		return re;
	}

	public void setRe(Double re) {
		this.re = re;
	}

	public Double getRr() {
		return rr;
	}

	public void setRr(Double rr) {
		this.rr = rr;
	}

	public Double getReCmAno() {
		return reCmAno;
	}

	public void setReCmAno(Double reCmAno) {
		this.reCmAno = reCmAno;
	}

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}

	public Geometry getShape() {
		return shape;
	}

	public void setShape(Geometry shape) {
		this.shape = shape;
	}

	public String getGdbGeomAttrData() {
		return gdbGeomAttrData;
	}

	public void setGdbGeomAttrData(String gdbGeomAttrData) {
		this.gdbGeomAttrData = gdbGeomAttrData;
	}
	
	

}
