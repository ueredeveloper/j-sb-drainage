package com.api.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	@Transient
	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomattrData;

	public HidrogeoFraturado() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHidrogeo() {
		return hidrogeo;
	}

	public void setHidrogeo(String hidrogeo) {
		this.hidrogeo = hidrogeo;
	}

	public Double getVazao() {
		return vazao;
	}

	public void setVazao(Double vazao) {
		this.vazao = vazao;
	}

	public Double getAreaSqM() {
		return areaSqM;
	}

	public void setAreaSqM(Double areaSqM) {
		this.areaSqM = areaSqM;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(String subsistema) {
		this.subsistema = subsistema;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Double getRrCmAno() {
		return rrCmAno;
	}

	public void setRrCmAno(Double rrCmAno) {
		this.rrCmAno = rrCmAno;
	}

	public Double getEspRaso() {
		return espRaso;
	}

	public void setEspRaso(Double espRaso) {
		this.espRaso = espRaso;
	}

	public Double getIfr() {
		return ifr;
	}

	public void setIfr(Double ifr) {
		this.ifr = ifr;
	}

	public Double getRprCmAn() {
		return rprCmAn;
	}

	public void setRprCmAn(Double rprCmAn) {
		this.rprCmAn = rprCmAn;
	}

	public Double getEspProfund() {
		return espProfund;
	}

	public void setEspProfund(Double espProfund) {
		this.espProfund = espProfund;
	}

	public Double getIfp() {
		return ifp;
	}

	public void setIfp(Double ifp) {
		this.ifp = ifp;
	}

	public Double getRppCmAn() {
		return rppCmAn;
	}

	public void setRppCmAn(Double rppCmAn) {
		this.rppCmAn = rppCmAn;
	}

	public Double getRpCmAno() {
		return rpCmAno;
	}

	public void setRpCmAno(Double rpCmAno) {
		this.rpCmAno = rpCmAno;
	}

	public Double getfRpd() {
		return fRpd;
	}

	public void setfRpd(Double fRpd) {
		this.fRpd = fRpd;
	}

	public Double getRpd() {
		return rpd;
	}

	public void setRpd(Double rpd) {
		this.rpd = rpd;
	}

	public Double getReCmAn() {
		return reCmAn;
	}

	public void setReCmAn(Double reCmAn) {
		this.reCmAn = reCmAn;
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

	public String getGdbGeomattrData() {
		return gdbGeomattrData;
	}

	public void setGdbGeomattrData(String gdbGeomattrData) {
		this.gdbGeomattrData = gdbGeomattrData;
	}
	
	

}
