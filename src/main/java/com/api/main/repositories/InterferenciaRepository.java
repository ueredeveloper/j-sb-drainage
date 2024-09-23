package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaModel;

@Repository
public interface InterferenciaRepository extends JpaRepository<InterferenciaModel, Long> {

	@Query("SELECT " 
		    + "CONCAT('{', '\"interferencia\"', ':', '{', " 
		    +  "'\"id\"', ':', i.id, ',', " 
		    +  "'\"latitude\"', ':', '\"', COALESCE(i.latitude, ''), '\"', ',', " 
		    +  "'\"longitude\"', ':', '\"', COALESCE(i.longitude, ''), '\"', ',', " 
		    +  "'\"endereco\"', ':', "
		    +  "CASE WHEN e.id IS NOT NULL " 
		    +  "THEN CONCAT('{', '\"id\"', ':', e.id, ',', '\"logradouro\"', ':', '\"', e.logradouro, '\"', '}') " 
		    +  "ELSE 'null' " 
		    +  "END, ',', "
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
			+  "CASE WHEN to.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', so.id, ',', '\"descricao\"', ':', '\"', so.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"situacaoProcesso\"', ':', "
			+  "CASE WHEN to.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', sp.id, ',', '\"descricao\"', ':', '\"', sp.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
			+  "'\"tipoAto\"', ':', "
			+  "CASE WHEN ta.id IS NOT NULL " 
			+  "THEN CONCAT('{', '\"id\"', ':', ta.id, ',', '\"descricao\"', ':', '\"', ta.descricao, '\"', '}') " 
			+  "ELSE 'null' " 
			+  "END, ',', "
		    +  "'\"', 'finalidades', '\"', ':', '[', "
		    +  "COALESCE(STRING_AGG(CONCAT("
		    +  "'{', '\"id\"', ':' , '\"', f.id,'\"' , ',',"
		    +  "'\"tipoFinalidade\"', ':' , '{\"', 'id','\"',':', '\"', tf.id, '\"', ',', '\"descricao\"',':', '\"', tf.descricao, '\"', '}',',',"
		    +  "'\"finalidade\"', ':' , '\"', f.finalidade,'\"' , ',',"
		    +  "'\"subfinalidade\"', ':' , '\"', f.subfinalidade,'\"' , ',',"
		    +  "'\"quantidade\"', ':' , '\"', f.quantidade,'\"' , ',',"
		    +  "'\"consumo\"', ':' , '\"', f.consumo,'\"' , ',',"
		    +  "'\"total\"', ':' , '\"', f.total,'\"' , '}'"
		    + "), ',') , '')"
		    +  ",'],', "
		    
		    + "'\"', 'demandas', '\"', ':', '[', "
		    +  "COALESCE(STRING_AGG(CONCAT("
		    +  "'{', '\"id\"', ':' , '\"', f.id,'\"' , ',',"
		    +  "'\"tipoFinalidade\"', ':' , '{\"', 'id','\"',':', '\"', tf.id, '\"', ',', '\"descricao\"',':', '\"', tf.descricao, '\"', '}',',',"
		    +  "'\"vazao\"', ':' , '\"', d.vazao,'\"' , ',',"
		    +  "'\"tempo\"', ':' , '\"', d.tempo,'\"' , ',',"
		    +  "'\"periodo\"', ':' , '\"', d.periodo,'\"' , ',',"
		    +  "'\"mes\"', ':' , '\"', d.mes,'\"' , '}'"
		    + "), ',') , '')"
		    +  ",']', "
		    
		    +  "'}}') " 
	        + "FROM InterferenciaModel i "
	        + "LEFT JOIN i.tipoInterferencia ti "
	        + "LEFT JOIN i.tipoOutorga to "
	        + "LEFT JOIN i.subtipoOutorga so "
	        + "LEFT JOIN i.situacaoProcesso sp "
	        + "LEFT JOIN i.tipoAto ta "
	        + "LEFT JOIN i.finalidades f "
	        + "LEFT JOIN i.demandas d "
	        + "LEFT JOIN f.tipoFinalidade tf "
	        
	        + "LEFT JOIN i.endereco e "
	        + "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
	        + "GROUP BY i.id, i.latitude, i.longitude, ti.id, ti.descricao")
	List<Object> listByLogradouro(@Param("keyword") String keyword);

}
