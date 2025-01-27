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

@Entity
@Table(name = "unidades_hidrograficas")
public class UnidadeHidrograficaModel {

	@Id
	@Column(name = "objectid")
	private Long objectid;

	@Column(name = "id_bacia")
	private Long idBacia;

	@Column(name = "bacia_nome")
	private String baciaNome;

	@Column(name = "bacia_codi")
	private String baciaCodi;

	@Column(name = "subbacia_n")
	private String subbaciaN;

	@Column(name = "uh_nome")
	private String uhNome;

	@Column(name = "uh_label")
	private String uhLabel;

	@Column(name = "uh_codigo")
	private Long uhCodigo;

	@Column(name = "subbcia_co")
	private Long subbciaCo;

	@Column(name = "shape_leng")
	private Double shapeLeng;

	// Adiciona transient para ao salvar não ter erro de conversão desta tabela para json
	//  could not deserialize] with root cause
	//java.io.StreamCorruptedException: invalid stream header: 30313033
	@Transient
	@Column(name = "shape", columnDefinition = "geometry(Geometry, 4674)")
	private Geometry shape;

	@Column(name = "area_km_sq")
	private Double areaKmSq;

	@Column(name = "qmm_jan")
	private Double qmmJan;

	@Column(name = "qmm_fev")
	private Double qmmFev;

	@Column(name = "qmm_mar")
	private Double qmmMar;

	@Column(name = "qmm_abr")
	private Double qmmAbr;

	@Column(name = "qmm_mai")
	private Double qmmMai;

	@Column(name = "qmm_jun")
	private Double qmmJun;

	@Column(name = "qmm_jul")
	private Double qmmJul;

	@Column(name = "qmm_ago")
	private Double qmmAgo;

	@Column(name = "qmm_set")
	private Double qmmSet;

	@Column(name = "qmm_out")
	private Double qmmOut;

	@Column(name = "qmm_nov")
	private Double qmmNov;

	@Column(name = "qmm_dez")
	private Double qmmDez;

	@Column(name = "rg_hidro")
	private String rgHidro;

	@Column(name = "gdb_geomattr_data")
	private String gdbGeomattrData;

	@OneToMany(mappedBy = "unidadeHidrografica", fetch = FetchType.EAGER)
	private Set<InterferenciaModel> interferencias = new HashSet<>();

	public UnidadeHidrograficaModel() {
		super();
	}
	
	public UnidadeHidrograficaModel(Long objectid) {
		super();
		this.objectid = objectid;
	}

	public Long getObjectid() {
		return objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public Long getIdBacia() {
		return idBacia;
	}

	public void setIdBacia(Long idBacia) {
		this.idBacia = idBacia;
	}

	public String getBaciaNome() {
		return baciaNome;
	}

	public void setBaciaNome(String baciaNome) {
		this.baciaNome = baciaNome;
	}

	public String getBaciaCodi() {
		return baciaCodi;
	}

	public void setBaciaCodi(String baciaCodi) {
		this.baciaCodi = baciaCodi;
	}

	public String getSubbaciaN() {
		return subbaciaN;
	}

	public void setSubbaciaN(String subbaciaN) {
		this.subbaciaN = subbaciaN;
	}

	public String getUhNome() {
		return uhNome;
	}

	public void setUhNome(String uhNome) {
		this.uhNome = uhNome;
	}

	public String getUhLabel() {
		return uhLabel;
	}

	public void setUhLabel(String uhLabel) {
		this.uhLabel = uhLabel;
	}

	public Long getUhCodigo() {
		return uhCodigo;
	}

	public void setUhCodigo(Long uhCodigo) {
		this.uhCodigo = uhCodigo;
	}

	public Long getSubbciaCo() {
		return subbciaCo;
	}

	public void setSubbciaCo(Long subbciaCo) {
		this.subbciaCo = subbciaCo;
	}

	public Double getShapeLeng() {
		return shapeLeng;
	}

	public void setShapeLeng(Double shapeLeng) {
		this.shapeLeng = shapeLeng;
	}

	public Geometry getShape() {
		return shape;
	}

	public void setShape(Geometry shape) {
		this.shape = shape;
	}

	public Double getAreaKmSq() {
		return areaKmSq;
	}

	public void setAreaKmSq(Double areaKmSq) {
		this.areaKmSq = areaKmSq;
	}

	public Double getQmmJan() {
		return qmmJan;
	}

	public void setQmmJan(Double qmmJan) {
		this.qmmJan = qmmJan;
	}

	public Double getQmmFev() {
		return qmmFev;
	}

	public void setQmmFev(Double qmmFev) {
		this.qmmFev = qmmFev;
	}

	public Double getQmmMar() {
		return qmmMar;
	}

	public void setQmmMar(Double qmmMar) {
		this.qmmMar = qmmMar;
	}

	public Double getQmmAbr() {
		return qmmAbr;
	}

	public void setQmmAbr(Double qmmAbr) {
		this.qmmAbr = qmmAbr;
	}

	public Double getQmmMai() {
		return qmmMai;
	}

	public void setQmmMai(Double qmmMai) {
		this.qmmMai = qmmMai;
	}

	public Double getQmmJun() {
		return qmmJun;
	}

	public void setQmmJun(Double qmmJun) {
		this.qmmJun = qmmJun;
	}

	public Double getQmmJul() {
		return qmmJul;
	}

	public void setQmmJul(Double qmmJul) {
		this.qmmJul = qmmJul;
	}

	public Double getQmmAgo() {
		return qmmAgo;
	}

	public void setQmmAgo(Double qmmAgo) {
		this.qmmAgo = qmmAgo;
	}

	public Double getQmmSet() {
		return qmmSet;
	}

	public void setQmmSet(Double qmmSet) {
		this.qmmSet = qmmSet;
	}

	public Double getQmmOut() {
		return qmmOut;
	}

	public void setQmmOut(Double qmmOut) {
		this.qmmOut = qmmOut;
	}

	public Double getQmmNov() {
		return qmmNov;
	}

	public void setQmmNov(Double qmmNov) {
		this.qmmNov = qmmNov;
	}

	public Double getQmmDez() {
		return qmmDez;
	}

	public void setQmmDez(Double qmmDez) {
		this.qmmDez = qmmDez;
	}

	public String getRgHidro() {
		return rgHidro;
	}

	public void setRgHidro(String rgHidro) {
		this.rgHidro = rgHidro;
	}

	public String getGdbGeomattrData() {
		return gdbGeomattrData;
	}

	public void setGdbGeomattrData(String gdbGeomattrData) {
		this.gdbGeomattrData = gdbGeomattrData;
	}

	public Set<InterferenciaModel> getInterferencias() {
		return interferencias;
	}

	public void setInterferencias(Set<InterferenciaModel> interferencias) {
		this.interferencias = interferencias;
	}
	
	

}