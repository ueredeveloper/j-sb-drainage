package com.api.main.repositories;

import java.util.List;
import java.util.Set;

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
	
		/*@Query("SELECT CONCAT('{', " +
		       "'\"id\"', ':', _d.id, ',', " +
		       "'\"numero\"', ':', '\"', COALESCE(_d.numero, ''), '\"', ',', " +
		       "'\"numeroSei\"', ':', '\"', COALESCE(_d.numeroSei, ''), '\"', ',', " +
		       "'\"endereco\"', ':', 'null', ','," +
		       "'\"processo\"', ':', 'null', ','," +
		       "'\"usuarios\"', ':', '[]', ','," +
		       "'\"tipoDocumento\"', ':', " +
		       "CASE WHEN _d.tipoDocumento.id IS NOT NULL " + 
		       "THEN CONCAT('{', '\"id\"', ':', _d.tipoDocumento.id, ',', '\"descricao\"', ':','\"', _d.tipoDocumento.descricao, '\"', '}') ELSE 'null' END," +
		       
		       "'}' " +
		       ") " +
		       "FROM DocumentoModel _d " +
		       "JOIN UsuarioDocumento ud ON _d.id = ud.documentoId " +
		       "JOIN UsuarioModel u ON u.id = ud.usuarioId " +
		       "WHERE u.id = id")

		List<Object> listByUserId (@Param("id") Long id);*/
	
	
	@Query(value= "SELECT CONCAT('{', " +
		       "'\"id\"', ':', _d.id, ',', " +
		       "'\"numero\"', ':', '\"', COALESCE(_d.numero, ''), '\"', ',', " +
		       "'\"numeroSei\"', ':', '\"', COALESCE(_d.numero_sei, ''), '\"', ',', " +
		       "'\"endereco\"', ':', 'null', ','," +
		       "'\"processo\"', ':', 'null', ','," +
		       "'\"usuarios\"', ':', '[]', ','," +
		       "'\"tipoDocumento\"', ':', " +
		       "CASE WHEN _d.tipo_documento IS NOT NULL " + 
		       "THEN CONCAT('{', '\"id\"', ':', _d.tipo_documento, ',', '\"descricao\"', ':','\"''\"', '}') ELSE 'null' END," +
		       
		       "'}' " +
		       ") " +
		       "FROM usuario_documento _ud " +
               "JOIN documento _d ON _d.id = _ud.documento_id " +
               "JOIN usuario _u ON _u.id = _ud.usuario_id " +
		       "WHERE _u.id = :userId", nativeQuery=true)

		List<Object> listByUserId (@Param("userId") Long userId);
		
}