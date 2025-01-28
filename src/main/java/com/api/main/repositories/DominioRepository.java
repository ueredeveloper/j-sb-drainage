package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.main.models.TipoInterferenciaModel;


public interface DominioRepository extends JpaRepository<TipoInterferenciaModel, Long> {
	
	/*
	 * @Query(value =
	 * "SELECT _ta.id AS tipoAtoId, _ta.descricao AS tipoAtoDescricao, " +
	 * "_ti.id AS tipoInterferenciaId, _ti.descricao AS tipoInterferenciaDescricao " +
	 * "FROM TIPO_ATO ta, TIPO_INTERFERENCIA ti", nativeQuery = true) List<Object[]>
	 * listAll();
	 */
	@Query(value = "SELECT 'TipoAto', _ta.id AS id, _ta.descricao AS descricao FROM TIPO_ATO _ta "
			+ "UNION ALL SELECT 'TipoInterferencia', _ti.id AS id, _ti.descricao AS descricao FROM TIPO_INTERFERENCIA _ti "
			+ "UNION All SELECT 'TipoOutorga', _to.id AS id, _to.descricao AS descricao FROM TIPO_OUTORGA _to "
			+ "UNION All SELECT 'SubtipoOutorga', _so.id AS id, _so.descricao AS descricao FROM SUBTIPO_OUTORGA _so "
			+ "UNION All SELECT 'Estado', _e.id AS id, _e.descricao AS descricao FROM ESTADO _e "
			+ "UNION All SELECT 'SituacaoProcesso', _sp.id AS id, _sp.descricao AS descricao FROM SITUACAO_PROCESSO _sp "
			+ "UNION All SELECT 'Subsistema', _s.id AS id, _s.descricao AS descricao FROM SUBSISTEMA _s "
			+ "UNION All SELECT 'TipoPoco', _tp.id AS id, _tp.descricao AS descricao FROM TIPO_POCO _tp "
			+ "UNION All SELECT 'LocalCaptacao', _lc.id AS id, _lc.descricao AS descricao FROM LOCAL_CAPTACAO _lc "
			, nativeQuery = true)
	List<Object[]> listAll();
	
	
	/*"SELECT _ta.id id, _ta.descricao descricao FROM TIPO_ATO ta "
	[
	    [
	        1,
	        "Resolução"
	    ],
	    [
	        2,
	        "Despacho"
	    ]
	]*/

}
