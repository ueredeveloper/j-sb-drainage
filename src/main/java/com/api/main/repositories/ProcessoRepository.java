package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoModel;


@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {

	
	@Query("SELECT CONCAT('{', " +
		       "'\"processo\"', ':', '{', " +
		           "'\"id\"', ':', _p.id, ',', " +
		           "'\"numero\"', ':', '\"', COALESCE(_p.numero, ''), '\"', ',', " +
		           
		           "'\"usuario\"', ':', " +
		           "CASE WHEN _u.id IS NOT NULL " +
		               "THEN CONCAT('{', '\"id\"', ':', _u.id, ',', '\"nome\"', ':', '\"', COALESCE(_u.nome, ''), '\"', '}') " +
		           "ELSE 'null' " +
		           "END, ',', " +
		           
		           "'\"anexo\"', ':', " +
		           "CASE WHEN _a.id IS NOT NULL " +
		               "THEN CONCAT('{', '\"id\"', ':', _a.id, ',', '\"numero\"', ':', '\"', COALESCE(_a.numero, ''), '\"', '}') " +
		           "ELSE 'null' " +
		           "END, " +
		       "'}', '}') " +
		       "FROM ProcessoModel _p " + 
		       "LEFT JOIN _p.anexo _a " +
		       "LEFT JOIN _p.usuario _u " +
		       "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_p.numero) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
		       "OR (:keyword IS NULL OR :keyword = '' OR LOWER(_u.nome) LIKE LOWER(CONCAT('%', :keyword, '%')))")
		List<Object> listByKeyword(@Param("keyword") String keyword);




}
