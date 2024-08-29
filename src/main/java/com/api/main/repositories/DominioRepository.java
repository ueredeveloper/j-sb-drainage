package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.main.models.TipoInterferenciaModel;

public interface DominioRepository extends JpaRepository<TipoInterferenciaModel, Long> {

	/*
	 * @Query(value =
	 * "SELECT ta.id AS tipoAtoId, ta.descricao AS tipoAtoDescricao, " +
	 * "ti.id AS tipoInterferenciaId, ti.descricao AS tipoInterferenciaDescricao " +
	 * "FROM TIPO_ATO ta, TIPO_INTERFERENCIA ti", nativeQuery = true) List<Object[]>
	 * listAll();
	 */
	@Query(value = "SELECT 'TipoAto', ta.id AS id, ta.descricao AS descricao FROM TIPO_ATO ta "
			+ "UNION ALL SELECT 'TipoInterferencia', ti.id AS id, ti.descricao AS descricao FROM TIPO_INTERFERENCIA ti "
			+ "UNION All SELECT 'TipoOutorga', to.id AS id, to.descricao AS descricao FROM TIPO_OUTORGA to "
			+ "UNION All SELECT 'SubtipoOutorga', so.id AS id, so.descricao AS descricao FROM SUBTIPO_OUTORGA so "
			+ "UNION All SELECT 'Estado', e.id AS id, e.descricao AS descricao FROM ESTADO e "
			+ "UNION All SELECT 'SituacaoProcesso', sp.id AS id, sp.descricao AS descricao FROM SITUACAO_PROCESSO sp "
			+ "UNION All SELECT 'Subsistema', s.id AS id, s.descricao AS descricao FROM SUBSISTEMA s "
			+ "UNION All SELECT 'TipoPoco', tp.id AS id, tp.descricao AS descricao FROM TIPO_POCO tp "
			+ "UNION All SELECT 'LocalCaptacao', lc.id AS id, lc.descricao AS descricao FROM LOCAL_CAPTACAO lc "
			, nativeQuery = true)
	List<Object[]> listAll();
	
	
	/*"SELECT ta.id id, ta.descricao descricao FROM TIPO_ATO ta "
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
