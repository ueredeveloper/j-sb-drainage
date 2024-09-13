package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
	
	/*@Query("SELECT d FROM DocumentoModel d "
		    + "LEFT JOIN d.processo p "
		    + "LEFT JOIN d.endereco e "
		    + "LEFT JOIN p.anexo a "
		    + "WHERE "
		    + "LOWER(d.numero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(d.numeroSei) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(p.numero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(a.numero) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<DocumentoModel> listByKeyword (@Param("keyword") String keyword);*/
	
	@Query("SELECT " +
		       "CONCAT('{', '\"documento\"', ':', '{', " +
		       "'\"id\"', ':', d.id, ',', " +
		       "'\"numero\"', ':', '\"', COALESCE(d.numero, ''), '\"', ',', " +
		       "'\"numeroSei\"', ':', '\"', COALESCE(d.numeroSei, ''), '\"', ',', " +
		       "'\"endereco\"', ':', " +
		       "CASE WHEN e.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', e.id, ',', '\"logradouro\"', ':', '\"', e.logradouro, '\"', '}') " +
		       "ELSE 'null' " +
		       "END, ',', " +
		       
		       "'\"tipo\"', ':', " +
		       "CASE WHEN d.tipo.id IS NOT NULL " + 
		       "THEN CONCAT('{', '\"id\"', ':', d.tipo.id, ',', '\"descricao\"', ':','\"', d.tipo.descricao, '\"', '}') ELSE 'null' END,',', " +
		       
		       "'\"processo\"', ':', " +
		       "CASE WHEN p.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', p.id, ',', '\"numero\"', ':', '\"', p.numero, '\"', ',', " +
		       "'\"anexo\"', ':', " +
		       "CASE WHEN p.anexo.id IS NOT NULL " +
		       "THEN CONCAT('{', '\"id\"', ':', p.anexo.id, ',', '\"numero\"', ':', '\"', p.anexo.numero, '\"', '}') " +
		       "ELSE 'null' " +
		       "END, '}') " +
		       "ELSE 'null' " +
		       "END, '}}') " +
		       "FROM DocumentoModel d " +
		       "LEFT JOIN d.endereco e " +
		       "LEFT JOIN d.processo p " +
		       "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")

		List<Object> listByKeyword(@Param("keyword") String keyword);


	
		
}