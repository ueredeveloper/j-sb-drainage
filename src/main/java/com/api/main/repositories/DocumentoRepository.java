package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;


@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
	/**
	 * Seleciona documentos por uma palavra chave.
	 * 
	 * @param keyword
	 * @return
	 */
	@Query("SELECT " + "CONCAT('{', '\"documento\"', ':', '{', " + "'\"id\"', ':', _d.id, ',', "
			+ "'\"numero\"', ':', '\"', COALESCE(_d.numero, ''), '\"', ',', "
			+ "'\"numeroSei\"', ':', '\"', COALESCE(_d.numeroSei, ''), '\"', ',', " + "'\"endereco\"', ':', "
			+ "CASE WHEN _e.id IS NOT NULL "
			+ "THEN CONCAT('{', '\"id\"', ':', _e.id, ',', '\"logradouro\"', ':', '\"', _e.logradouro, '\"', '}') "
			+ "ELSE 'null' " + "END, ',', " +

			"'\"tipoDocumento\"', ':', " + "CASE WHEN _d.tipoDocumento.id IS NOT NULL "
			+ "THEN CONCAT('{', '\"id\"', ':', _d.tipoDocumento.id, ',', '\"descricao\"', ':','\"', _d.tipoDocumento.descricao, '\"', '}') ELSE 'null' END,',', "
			+

			"'\"processo\"', ':', " + "CASE WHEN _p.id IS NOT NULL "
			+ "THEN CONCAT('{', '\"id\"', ':', _p.id, ',', '\"numero\"', ':', '\"', _p.numero, '\"', ',', "
			+ "'\"anexo\"', ':', " + "CASE WHEN _p.anexo.id IS NOT NULL "
			+ "THEN CONCAT('{', '\"id\"', ':', _p.anexo.id, ',', '\"numero\"', ':', '\"', _p.anexo.numero, '\"', '}') "
			+ "ELSE 'null' " + "END, '}') " + "ELSE 'null' " + "END, '}}') " 
			+ "FROM DocumentoModel _d "
			+ "LEFT JOIN _d.endereco _e " 
			+ "LEFT JOIN _d.processo _p "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
			+ "OR (:keyword IS NULL OR :keyword = '' OR LOWER(_d.numero) LIKE LOWER(CONCAT('%', :keyword, '%')))")

	List<Object> listByKeyword1(@Param("keyword") String keyword);

	/**
	 * Seleciona documentos pelo id do usuário.
	 * 
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT CONCAT('{', " + "'\"id\"', ':', _d.id, ',', "
			+ "'\"numero\"', ':', '\"', COALESCE(_d.numero, ''), '\"', ',', "
			+ "'\"numeroSei\"', ':', '\"', COALESCE(_d.numero_sei, ''), '\"', ',', "
			+ "'\"endereco\"', ':', 'null', ','," + "'\"processo\"', ':', 'null', ',',"
			+ "'\"usuarios\"', ':', '[]', ','," + "'\"tipoDocumento\"', ':', "
			+ "CASE WHEN _d.tipo_documento IS NOT NULL "
			+ "THEN CONCAT('{', '\"id\"', ':', _d.tipo_documento, ',', '\"descricao\"', ':','\"''\"', '}') ELSE 'null' END,"
			+

			"'}' " + ") " + "FROM usuario_documento _ud " + "JOIN documento _d ON _d.id = _ud.documento_id "
			+ "JOIN usuario _u ON _u.id = _ud.usuario_id " + "WHERE _u.id = :userId", nativeQuery = true)

	List<Object> listByUserId(@Param("userId") Long userId);
	
	
	@Query(value = "SELECT CONCAT (\r\n"
			+ "'{', \r\n"
			+ "'\"documento\"', ':', \r\n"
			+ "'{'\r\n"
			+ "'\"id\"',':','\"', _d.id,'\"',',',\r\n"
			+ "'\"numero\"',':','\"', _d.numero,'\"',',',\r\n"
			+ "'\"numeroSei\"',':','\"', _d.numero_sei,'\"',',',\r\n"
			+ "\r\n"
			+ "'\"endereco\"', ':',\r\n"
			+ "CASE WHEN _e.id IS NOT NULL\r\n"
			+ "THEN CONCAT('{', \r\n"
			+ "'\"','id','\"',':','\"',_e.id,'\"',',',\r\n"
			+ "'\"','logradouro','\"',':','\"',_e.logradouro,'\"',\r\n"
			+ "'}'\r\n"
			+ ")\r\n"
			+ "ELSE 'null'\r\n"
			+ "END, ','\r\n"
			+ "'\"', 'tipoDocumento', '\"', ':',\r\n"
			+ "CASE WHEN _td.id IS NOT NULL\r\n"
			+ "THEN CONCAT('{', \r\n"
			+ "'\"','id','\"',':','\"',_td.id,'\"',',',\r\n"
			+ "'\"','descricao','\"',':','\"',_td.descricao,'\"',\r\n"
			+ "'}'\r\n"
			+ ")\r\n"
			+ "ELSE 'null'\r\n"
			+ "END, ','\r\n"
			+ "'\"', 'processo', '\"', ':',\r\n"
			+ "CASE WHEN _p.id IS NOT NULL\r\n"
			+ "THEN CONCAT('{', \r\n"
			+ "'\"','id','\"',':','\"',_p.id,'\"',',',\r\n"
			+ "'\"','numero','\"',':','\"',_p.numero,'\"',',',\r\n"
			+ "'\"','anexo','\"',':',\r\n"
			+ "CASE WHEN _a.id IS NOT NULL\r\n"
			+ "THEN CONCAT('{', \r\n"
			+ "'\"','id','\"',':','\"',_a.id,'\"',',',\r\n"
			+ "'\"','numero','\"',':','\"',_a.numero,'\"',\r\n"
			+ "'}'\r\n"
			+ ")\r\n"
			+ "ELSE 'null'\r\n"
			+ "END, \r\n"
			+ "'}'\r\n"
			+ ")\r\n"
			+ "ELSE 'null'\r\n"
			+ "END\r\n"
			+ ",'}'\r\n"
			+ ",'}'\r\n"
			+ ")\r\n"
			+ "from documento _d\r\n"
			+ "LEFT JOIN endereco _e on _e.id = _d.endereco\r\n"
			+ "LEFT JOIN documento_tipo _td on _td.id = _d.tipo_documento\r\n"
			+ "LEFT JOIN processo _p on _p.id = _d.processo\r\n"
			+ "LEFT JOIN anexo _a on _p.anexo = _a.id\r\n"
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(_e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
			+ "OR (:keyword IS NULL OR :keyword = '' OR LOWER(_d.numero) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
			+ "OR (:keyword IS NULL OR :keyword = '' OR LOWER(_d.numero_sei) LIKE LOWER(CONCAT('%', :keyword, '%')))"
			, nativeQuery = true)

	List<Object> listByKeyword(@Param("keyword") String keyword);
	
	

	/**
	 * Delete o relacionamento entre um usuário e um documento.
	 * 
	 * @param docId
	 */

	@Query(value = "DELETE FROM usuario_documento WHERE documento_id = :docId AND usuario_id = :usId RETURNING documento_id", nativeQuery = true)
	Long deleteDocUseRelation(@Param("docId") Long docId, @Param("usId") Long usId);

}