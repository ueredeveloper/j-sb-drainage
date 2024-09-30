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
		    +  "'\"id\"', ':', i.id, ',', " 
		    +  "'\"latitude\"', ':', '\"', COALESCE(i.latitude, ''), '\"', ',', " 
		    +  "'\"longitude\"', ':', '\"', COALESCE(i.longitude, ''), '\"', ',' ,"
		    +  "'\"vazaoOutorgavel\"', ':', COALESCE(s.vazao_outorgavel, 0), ',' ,"
		    +  "'\"vazaoTeste\"', ':', COALESCE(s.vazao_teste, 0), ',' ,"
		    +  "'\"vazaoSistema\"', ':', COALESCE(s.vazao_sistema, 0), ',' ,"
		    +  "'\"profundidade\"', ':', COALESCE(s.profundidade, '\"\"'), ',' ,"
		    +  "'\"nivelEstatico\"', ':', COALESCE(s.nivel_estatico, '\"\"'), ',' ,"
		    +  "'\"nivelDinamico\"', ':', COALESCE(s.nivel_dinamico, '\"\"'), ',' ,"
		    +  "'\"caesb\"', ':', COALESCE(s.caesb, 'false'), ',' ,"
		    + "'\"tipoPoco\"', ':', "
		    + "COALESCE(CONCAT('{', '\"id\"', ':', s.tipo_poco, '}'), 'null'), ',', "
		    + "'\"endereco\"', ':', "
		    + "COALESCE(CONCAT('{', '\"id\"', ':', e.id, ',', '\"logradouro\"', ':', '\"', e.logradouro, '\"', '}'), 'null'), ',', "
		    +  "'\"tipoInterferencia\"', ':', "
			+  "CASE WHEN ti.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', ti.id, ',', '\"descricao\"', ':', '\"', ti.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"tipoOutorga\"', ':', "
			+  "CASE WHEN to.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', to.id, ',', '\"descricao\"', ':', '\"', to.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"subtipoOutorga\"', ':', "
			+  "CASE WHEN so.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', so.id, ',', '\"descricao\"', ':', '\"', so.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"situacaoProcesso\"', ':', "
			+  "CASE WHEN sp.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', sp.id, ',', '\"descricao\"', ':', '\"', sp.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"tipoAto\"', ':', "
			+  "CASE WHEN ta.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', ta.id, ',', '\"descricao\"', ':', '\"', ta.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
		    +  "'\"finalidades\"', ':', '[', "
		    +  "COALESCE(STRING_AGG(DISTINCT CONCAT(" 
		    +  "'{', '\"id\"', ':' , '\"', f.id,'\"' , ',',"
		    +  "'\"tipoFinalidade\"', ':' , '{\"id\":\"', f.tipo_finalidade, '\"}',',',"
		    +  "'\"finalidade\"', ':' , '\"', f.finalidade,'\"' , ',',"
		    +  "'\"subfinalidade\"', ':' , '\"', f.subfinalidade,'\"' , ',',"
		    +  "'\"quantidade\"', ':' , '\"', f.quantidade,'\"' , ',',"
		    +  "'\"consumo\"', ':' , '\"', f.consumo,'\"' , ',',"
		    +  "'\"total\"', ':' , '\"', f.total,'\"' , '}'"
		    + "), ',') , '')"
		    +  ",'],', "
		    +  "'\"demandas\"', ':', '[', "
			+  "COALESCE(STRING_AGG(DISTINCT CONCAT(" 
			+  "'{', '\"id\"', ':' , '\"', d.id,'\"' , ',',"
			+  "'\"tipoFinalidade\"', ':' , '{\"id\":\"', d.tipo_finalidade, '\"}',',',"
			+  "'\"mes\"', ':' , '\"', d.mes,'\"' , ',',"
			+  "'\"periodo\"', ':' , '\"', d.periodo,'\"' , ',',"
			+  "'\"tempo\"', ':' , '\"', d.tempo,'\"' , ',',"
			//+  "'\"interferencia\"', ':' , '{\"id\":\"', d.interferencia, '\"}',',',"
			+  "'\"vazao\"', ':' , '\"', d.vazao,'\"' , '}'"
			+ "), ',') , '')"
			
			+  ",']', "
		    +  "'}}') " 
            + "FROM interferencia i " 
            + "LEFT JOIN finalidade f ON f.interferencia = i.id " 
            + "LEFT JOIN demanda d ON d.interferencia = i.id " 
            + "LEFT JOIN tipo_interferencia ti ON ti.id = i.tipo_interferencia "
            + "LEFT JOIN tipo_outorga to ON to.id = i.tipo_outorga "
            + "LEFT JOIN subtipo_outorga so ON so.id = i.subtipo_outorga "
            + "LEFT JOIN situacao_processo sp ON sp.id = i.situacao_processo "
            + "LEFT JOIN tipo_ato ta ON ta.id = i.tipo_ato "
            + "LEFT JOIN endereco e ON e.id = i.endereco " 
            + "LEFT JOIN subterranea s ON i.id = s.id "
            + "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))"
            + "GROUP BY i.id, i.latitude, i.longitude, e.id, e.logradouro",
    nativeQuery = true)
	
	Set<String> listByLogradouro(@Param("keyword") String keyword);

}
