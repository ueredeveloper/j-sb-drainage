package com.api.main.dto;

import java.util.Map;

public class DominioDTO {

	// v1.12.2
	private Map<Long, TipoInterferenciaDTO> tipoInterferencia;
	private Map<Long, TipoAtoDTO> tipoAto;
	private Map<Long, TipoOutorgaDTO> tipoOutorga;
	private Map<Long, SubtipoOutorgaDTO> subtipoOutorga;
	private Map<Long, EstadoDTO> estado;
	private Map<Long, SituacaoProcessoDTO> situacaoProcesso;
	private Map<Long, SubsistemaDTO> subsistema;
	private Map<Long, TipoPocoDTO> tipoPoco;
	private Map<Long, LocalCaptacaoDTO> localCaptacao;

	public DominioDTO() {
		super();
	}

	public DominioDTO(Map<Long, TipoInterferenciaDTO> tipoInterferencia, Map<Long, TipoAtoDTO> tipoAto,
			Map<Long, TipoOutorgaDTO> tipoOutorga, Map<Long, SubtipoOutorgaDTO> subtipoOutorga,
			Map<Long, EstadoDTO> estado, Map<Long, SituacaoProcessoDTO> situacaoProcesso,
			Map<Long, SubsistemaDTO> subsistema, Map<Long, TipoPocoDTO> tipoPoco,
			Map<Long, LocalCaptacaoDTO> localCaptacao) {
		super();
		this.tipoInterferencia = tipoInterferencia;
		this.tipoAto = tipoAto;
		this.tipoOutorga = tipoOutorga;
		this.subtipoOutorga = subtipoOutorga;
		this.estado = estado;
		this.situacaoProcesso = situacaoProcesso;
		this.subsistema = subsistema;
		this.tipoPoco = tipoPoco;
		this.localCaptacao = localCaptacao;
	}

	public Map<Long, TipoInterferenciaDTO> getTipoInterferencia() {
		return tipoInterferencia;
	}

	public void setTipoInterferencia(Map<Long, TipoInterferenciaDTO> tipoInterferencia) {
		this.tipoInterferencia = tipoInterferencia;
	}

	public Map<Long, TipoAtoDTO> getTipoAto() {
		return tipoAto;
	}

	public void setTipoAto(Map<Long, TipoAtoDTO> tipoAto) {
		this.tipoAto = tipoAto;
	}

	public Map<Long, TipoOutorgaDTO> getTipoOutorga() {
		return tipoOutorga;
	}

	public void setTipoOutorga(Map<Long, TipoOutorgaDTO> tipoOutorga) {
		this.tipoOutorga = tipoOutorga;
	}

	public Map<Long, SubtipoOutorgaDTO> getSubtipoOutorga() {
		return subtipoOutorga;
	}

	public void setSubtipoOutorga(Map<Long, SubtipoOutorgaDTO> subtipoOutorga) {
		this.subtipoOutorga = subtipoOutorga;
	}

	public Map<Long, EstadoDTO> getEstado() {
		return estado;
	}

	public void setEstado(Map<Long, EstadoDTO> estado) {
		this.estado = estado;
	}

	public Map<Long, SituacaoProcessoDTO> getSituacaoProcesso() {
		return situacaoProcesso;
	}

	public void setSituacaoProcesso(Map<Long, SituacaoProcessoDTO> situacaoProcesso) {
		this.situacaoProcesso = situacaoProcesso;
	}

	public Map<Long, SubsistemaDTO> getSubsistema() {
		return subsistema;
	}

	public void setSubsistema(Map<Long, SubsistemaDTO> subsistema) {
		this.subsistema = subsistema;
	}

	public Map<Long, TipoPocoDTO> getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(Map<Long, TipoPocoDTO> tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public Map<Long, LocalCaptacaoDTO> getLocalCaptacao() {
		return localCaptacao;
	}

	public void setLocalCaptacao(Map<Long, LocalCaptacaoDTO> localCaptacao) {
		this.localCaptacao = localCaptacao;
	}

}
