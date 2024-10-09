package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
	
	@Query("SELECT " +
		       "CONCAT('{', '\"documento\"', ':', '{', " +
		       "'\"id\"', ':', _d.id, ',', " +
		       "'\"numero\"', ':', '\"', COALESCE(_d.numero, ''), '\"', ',', " +
		       "'\"numeroSei\"', ':', '\"', COALESCE(_d.numeroSei, ''), '\"', ',', " +
		       "'\"endereco\"', ':', " +
		       "CASE WHEN _e.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', _e.id, ',', '\"logradouro\"', ':', '\"', _e.logradouro, '\"', '}') " +
		       "ELSE 'null' " +
		       "END, ',', " +
		       
		       "'\"tipoDocumento\"', ':', " +
		       "CASE WHEN _d.tipoDocumento.id IS NOT NULL " + 
		       "THEN CONCAT('{', '\"id\"', ':', _d.tipoDocumento.id, ',', '\"descricao\"', ':','\"', _d.tipoDocumento.descricao, '\"', '}') ELSE 'null' END,',', " +
		       
		       "'\"processo\"', ':', " +
		       "CASE WHEN _p.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', _p.id, ',', '\"numero\"', ':', '\"', _p.numero, '\"', ',', " +
		       "'\"anexo\"', ':', " +
		       "CASE WHEN _p.anexo.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', _p.anexo.id, ',', '\"numero\"', ':', '\"', _p.anexo.numero, '\"', '}') " +
		       "ELSE 'null' " +
		       "END, '}') " +
		       "ELSE 'null' " +
		       "END, '}}') " +
		       "FROM DocumentoModel _d " +
		       "LEFT JOIN _d.endereco _e " +
		       "LEFT JOIN _d.processo _p " +
		       "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")

		List<Object> listByKeyword(@Param("keyword") String keyword);
	



	
		
}