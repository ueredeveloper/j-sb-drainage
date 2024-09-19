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
		       "'\"id\"', ':', p.id, ',', " +
		       "'\"numero\"', ':', '\"', COALESCE(p.numero, ''), '\"', ',', " +
		       "'\"anexo\"', ':', " +
		       "CASE WHEN p.anexo.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', p.anexo.id, ',', '\"numero\"', ':', '\"', COALESCE(p.anexo.numero, ''), '\"', '}') " +
		       "ELSE 'null' " +
		       "END, '}', '}') " +
		       "FROM ProcessoModel p " +
		       "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(p.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))")
		List<Object> listByKeyword(@Param("keyword") String keyword);


}
