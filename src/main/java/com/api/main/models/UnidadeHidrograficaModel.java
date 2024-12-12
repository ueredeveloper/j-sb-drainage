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

@Entity
@Table(name = "unidades_hidrograficas")
public class UnidadeHidrograficaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_bacia")
	private Long idBacia;
	
	@Column(name = "objectid")
	private Long objectid;
	
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

	@Column(name = "shape", columnDefinition = "geometry(POLYGON, 4674)")
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

}