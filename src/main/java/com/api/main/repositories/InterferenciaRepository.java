package com.api.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaModel;


@Repository
public interface InterferenciaRepository extends JpaRepository<InterferenciaModel, Long> {

	@Query(value = "SELECT " 
			+ "CONCAT('{', '\"interferencia\"', ':', '{', " 
		    +  "'\"id\"', ':', _i.id, ',', " 
		    +  "'\"latitude\"', ':', '\"', COALESCE(_i.latitude, null), '\"', ',', " 
		    +  "'\"longitude\"', ':', '\"', COALESCE(_i.longitude, null), '\"', ',' ,"
		    +  "'\"vazaoOutorgavel\"', ':', COALESCE(_s.vazao_outorgavel, 0), ',' ,"
		    +  "'\"vazaoTeste\"', ':', COALESCE(_s.vazao_teste, 0), ',' ,"
		    +  "'\"vazaoSistema\"', ':', COALESCE(_s.vazao_sistema, 0), ',' ,"
		    +  "'\"profundidade\"', ':', '\"', COALESCE(NULLIF(_s.profundidade, ''), '0'), '\"', ',', "
	        +  "'\"nivelEstatico\"', ':', '\"', COALESCE(NULLIF(_s.nivel_estatico, ''), '0'), '\"', ',', "
	        +  "'\"nivelDinamico\"', ':', '\"', COALESCE(NULLIF(_s.nivel_dinamico, ''), '0'), '\"', ',', "
	        
		    +  "'\"caesb\"', ':', COALESCE(CASE WHEN _s.caesb THEN 'true' ELSE 'false' END, 'false'), ',' ,"
		    +  "'\"tipoPoco\"', ':', "
		    +  "CASE WHEN _s.tipo_poco IS NOT NULL "
		    +  "THEN CONCAT('{', '\"id\"', ':', _s.tipo_poco, '}') "
		    +  "ELSE 'null' "
		    +  "END, ',', "
		    
			+  "'\"unidadeHidrografica\"', ':', "
			+  "CASE WHEN _i.unidade_hidrografica IS NOT NULL "
			+  "THEN CONCAT('{', '\"objectid\"', ':', _i.unidade_hidrografica, '}') "
			+  "ELSE 'null' "
			+  "END, ',', "
			
			+  "'\"baciaHidrografica\"', ':', "
			+  "CASE WHEN _i.bacia_hidrografica IS NOT NULL "
			+  "THEN CONCAT('{', '\"objectid\"', ':', _i.bacia_hidrografica, '}') "
			+  "ELSE 'null' "
			+  "END, ',', "
			
		    +  "'\"endereco\"', ':', "
		    +  "COALESCE(CONCAT('{', '\"id\"', ':', _e.id, ',', '\"logradouro\"', ':', '\"', _e.logradouro, '\"', '}'), 'null'), ',', "
		    +  "'\"tipoInterferencia\"', ':', "
			+  "CASE WHEN _ti.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', _ti.id, ',', '\"descricao\"', ':', '\"', _ti.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"tipoOutorga\"', ':', "
			+  "CASE WHEN _to.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', _to.id, ',', '\"descricao\"', ':', '\"', _to.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"subtipoOutorga\"', ':', "
			+  "CASE WHEN _so.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', _so.id, ',', '\"descricao\"', ':', '\"', _so.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"situacaoProcesso\"', ':', "
			+  "CASE WHEN _sp.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', _sp.id, ',', '\"descricao\"', ':', '\"', _sp.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"tipoAto\"', ':', "
			+  "CASE WHEN _ta.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', _ta.id, ',', '\"descricao\"', ':', '\"', _ta.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+ "'\"finalidades\"', ':',"
			+ "CASE WHEN EXISTS (SELECT * FROM finalidade _f WHERE _f.interferencia = _s.id) "
			+ "THEN CONCAT('[', (SELECT STRING_AGG(CONCAT("
			+ "'{', '\"id\"', ':', _f.id, ',', "
			+ "'\"finalidade\"', ':', '\"', _f.finalidade, '\"', ',',"
			+ "'\"subfinalidade\"', ':', '\"', _f.subfinalidade, '\"', ',',"
			+ "'\"quantidade\"', ':', '\"', COALESCE(_f.quantidade, 0), '\"', ',',"
			+ "'\"consumo\"', ':', '\"', COALESCE(_f.consumo, 0), '\"', ',',"
			+ "'\"tipoFinalidade\"', ':', '{', '\"id\"', ':',_f.tipo_finalidade, '}', ',',"
			+ "'\"total\"', ':', '\"', COALESCE(_f.total, 0), '\"', "
			+ "'}'), ',')"
			+ "FROM finalidade _f WHERE _f.interferencia = _s.id), ']') "
		    + "ELSE '[]' END, ',', "
			
			+ "'\"demandas\"', ':',"
			+ "CASE WHEN EXISTS (SELECT * FROM finalidade _f WHERE _f.interferencia = _s.id) "
			+ "THEN CONCAT('[', (SELECT STRING_AGG(CONCAT("
			+ "'{', '\"id\"', ':', _d.id, ',',"
			+ "'\"tipoFinalidade\"', ':', '{', '\"id\"', ':',_d.tipo_finalidade, '}', ',',"
			+ "'\"mes\"', ':', _d.mes,',',"
			+ "'\"periodo\"', ':', COALESCE(_d.periodo, 0),',',"
			+ "'\"tempo\"', ':', COALESCE(_d.tempo, 0),',',"
			+ "'\"vazao\"', ':', COALESCE(_d.vazao, 0),"
			+ "'}'), ',')"
			+ "FROM demanda _d WHERE _d.interferencia = _s.id), ']')"
			+ "ELSE '[]' END"
		
		    +  ",'}}') " 
            + "FROM interferencia _i " 
            + "LEFT JOIN finalidade _f ON _f.interferencia = _i.id " 
            + "LEFT JOIN demanda _d ON _d.interferencia = _i.id " 
            + "LEFT JOIN tipo_interferencia _ti ON _ti.id = _i.tipo_interferencia "
            + "LEFT JOIN tipo_outorga _to ON _to.id = _i.tipo_outorga "
            + "LEFT JOIN subtipo_outorga _so ON _so.id = _i.subtipo_outorga "
            + "LEFT JOIN situacao_processo _sp ON _sp.id = _i.situacao_processo "
            + "LEFT JOIN tipo_ato _ta ON _ta.id = _i.tipo_ato "
            + "LEFT JOIN unidades_hidrograficas _uh ON _uh.objectid = _i.unidade_hidrografica "
            + "LEFT JOIN bacias_hidrograficas _ba ON _ba.objectid = _i.bacia_hidrografica "
            + "LEFT JOIN endereco _e ON _e.id = _i.endereco " 
            + "LEFT JOIN subterranea _s ON _i.id = _s.id "
            + "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "GROUP BY "
				 		+"_s.id, _d.id, _i.id,_i.latitude,_i.longitude,_e.id,_e.logradouro,_s.vazao_outorgavel,"
				 		+"_s.vazao_teste,_s.vazao_sistema,_s.profundidade,_s.nivel_estatico,_s.nivel_dinamico,"
				 		+"_s.caesb,_s.tipo_poco,_ti.id,_to.id,_so.id,_sp.id,_ta.id",
    nativeQuery = true)
	
	Set<String> listByLogradouro(@Param("keyword") String keyword);

}
