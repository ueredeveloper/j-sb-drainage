package com.api.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.UsuarioModel;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	@Query("SELECT _u FROM UsuarioModel _u " + "WHERE " + "LOWER (_u.nome) LIKE LOWER(concat('%', :name, '%')) ")
	Set<UsuarioModel> listUsersByName(@Param("name") String name);

	@Query(value = "SELECT * FROM usuario u JOIN usuario_documento ud ON u.id = ud.usuario_id WHERE ud.documento_id = :documentoId", nativeQuery = true)
	Set<UsuarioModel> listUsersByDocumentId(@Param("documentoId") Long documentoId);

	@Query("SELECT u FROM UsuarioModel u WHERE CAST(u.cpfCnpj AS string) LIKE %:cpfCnpj%")
	Set<UsuarioModel> findByCpfCnpjContaining(@Param("cpfCnpj") String cpfCnpj);

	@Query(value = "SELECT COUNT(ud) > 0 FROM usuario u " + "JOIN usuario_documento ud ON ud.usuario_id = u.id "
			+ "WHERE u.id = :usuarioId AND ud.documento_id = :documentoId", nativeQuery = true)
	boolean existsRelationship(@Param("usuarioId") Long usuarioId, @Param("documentoId") Long documentoId);
	
	
	 @Query(value = "SELECT _u.id AS us_id, " +
	            "_u.nome AS us_nome, " +
	            "_u.cpf_cnpj AS us_cpf_cnpj, " +
	            "_ud.documento_id us_doc_id," +
	            "_d.endereco AS doc_end, " +
	            "_d.numero_sei AS doc_sei, " +
	            "_d.processo AS proc_sei, " +
	            "_e.id AS end_id, " +
	            "_e.logradouro AS end_logradouro " +
	            "FROM usuario _u " +
	            "LEFT JOIN usuario_documento _ud ON _ud.usuario_id = _u.id " +
	            "LEFT JOIN documento _d ON _d.id = _ud.documento_id " +
	            "LEFT JOIN processo _p ON _p.id = _d.processo " +
	            "LEFT JOIN endereco _e ON _e.id = _d.endereco " +
	            "WHERE _u.nome LIKE LOWER(CONCAT('%', :keywork, '%')) " +
	            "OR LOWER(CAST(_u.cpf_cnpj AS VARCHAR)) LIKE LOWER(CONCAT('%', :keywork, '%')) " +
	            "OR LOWER(CAST(_d.numero_sei AS VARCHAR)) LIKE LOWER(CONCAT('%', :keywork, '%')) " +
	            "OR LOWER(_p.numero) LIKE LOWER(CONCAT('%', :keywork, '%'))", 
	            nativeQuery = true)
	    Set<Object[]> listUserByKeyword (@Param("keywork") String keywork);

}