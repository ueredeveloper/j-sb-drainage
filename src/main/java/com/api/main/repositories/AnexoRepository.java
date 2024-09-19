package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;

@Repository
public interface AnexoRepository extends JpaRepository<AnexoModel, Long> {
	

	/*@Query("SELECT DISTINCT a FROM AnexoModel a LEFT JOIN a.processos p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(a.numero) LIKE %:keyword%)")
	List<AnexoModel> listByKeyword(String keyword);*/
	
	@Query(value = 
		    "SELECT CONCAT('{', " +
		    "'\"anexo\"', ':', '{', " +
		    "'\"id\"', ':', a.id, ',', " +
		    "'\"numero\"', ':', '\"', a.numero, '\"', ',', " +
		    "'\"processos\"', ':', " +
		    "CASE WHEN EXISTS (SELECT 1 FROM processo p WHERE p.anexo = a.id) " +
		    "THEN CONCAT('[', (SELECT STRING_AGG(CONCAT('{', '\"id\"', ':', p.id, ',', '\"numero\"', ':', '\"', p.numero, '\"', '}'), ',') FROM processo p WHERE p.anexo = a.id), ']') " +
		    "ELSE '[]' END, " +
		    "'}', '}') " +
		    "FROM anexo a " +
		    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(a.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))",
		    nativeQuery = true)
		List<Object> listByKeyword(@Param("keyword") String keyword);

}
