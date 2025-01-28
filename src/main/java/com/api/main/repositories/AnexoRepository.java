package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;


@Repository
public interface AnexoRepository extends JpaRepository<AnexoModel, Long> {
	

	/*@Query("SELECT DISTINCT a FROM AnexoModel a LEFT JOIN _a.processos p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_a.numero) LIKE %:keyword%)")
	List<AnexoModel> listByKeyword(String keyword);*/
	
	@Query(value = 
		    "SELECT CONCAT('{', " +
		    "'\"anexo\"', ':', '{', " +
		    "'\"id\"', ':', _a.id, ',', " +
		    "'\"numero\"', ':', '\"', _a.numero, '\"', ',', " +
		    "'\"processos\"', ':', " +
		    "CASE WHEN EXISTS (SELECT 1 FROM processo _p WHERE _p.anexo = _a.id) " +
		    "THEN CONCAT('[', ("
		    + "SELECT STRING_AGG(CONCAT('{', '\"id\"', ':', _p.id, ',', '\"numero\"', ':', '\"', _p.numero, '\"', '}'), ',') "
		    + "FROM processo _p WHERE _p.anexo = _a.id), ']') " +
		    "ELSE '[]' END, " +
		    "'}', '}') " +
		    "FROM anexo _a " +
		    
		    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_a.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))",
		    nativeQuery = true)
		List<Object> listByKeyword(@Param("keyword") String keyword);

}
