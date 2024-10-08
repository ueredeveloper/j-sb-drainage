package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.FinalidadeModel;

@Repository
public interface FinalidadeRepository extends JpaRepository<FinalidadeModel, Long> {

	@Query("SELECT " + "CONCAT('{', '\"finalidade\"', ':', '{', " + "'\"id\"', ':', f.id, ',', "
			+ "'\"finalidade\"', ':','\"', COALESCE(f.finalidade, ''),'\"', ',', "
			+ "'\"subfinalidade\"', ':','\"', COALESCE(f.subfinalidade, ''),'\"', ',', "
			+ "'\"quantidade\"', ':', COALESCE(f.quantidade, ''), ',', "
			+ "'\"consumo\"', ':', COALESCE(f.consumo, ''), ',', " + "'\"tipoFinalidade\"', ':', "
			+ "CASE WHEN f.tipoFinalidade IS NOT NULL " + // Changed from `f.id` to `f.tipoFinalidade`
			"THEN CONCAT('{', '\"id\"', ':', f.tipoFinalidade.id, '},') " + "ELSE 'null' " + "END," +

			"'\"interferencia\"', ':', " + "CASE WHEN i.id IS NOT NULL " + "THEN CONCAT('{', '\"id\"', ':', i.id, '}') "
			+ "ELSE 'null' " + "END " + ",'}}') " + "FROM FinalidadeModel f " + "LEFT JOIN f.interferencia i "
			+ "WHERE i.id = :id") // Using a parameter for id
	List<Object> listByInterferenciaId(@Param("id") Long id);

}
