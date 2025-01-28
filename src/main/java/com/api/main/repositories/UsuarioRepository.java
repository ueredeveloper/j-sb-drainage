package com.api.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.UsuarioModel;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	@Query("SELECT u FROM UsuarioModel u " + "WHERE " + "LOWER (u.nome) LIKE LOWER(concat('%', :keyword, '%')) ")
	Set<UsuarioModel> listUsersByName(@Param("keyword") String keyword);

	@Query(value = "SELECT * FROM usuario u JOIN usuario_documento ud ON u.id = ud.usuario_id WHERE ud.documento_id = :documentoId", nativeQuery = true)
	Set<UsuarioModel> listUsersByDocumentId(@Param("documentoId") Long documentoId);

	@Query("SELECT u FROM UsuarioModel u WHERE CAST(u.cpfCnpj AS string) LIKE %:cpfCnpj%")
	Set<UsuarioModel> findByCpfCnpjContaining(@Param("cpfCnpj") String cpfCnpj);

	@Query(value = "SELECT COUNT(ud) > 0 FROM usuario u " + "JOIN usuario_documento ud ON ud.usuario_id = u.id "
			+ "WHERE u.id = :usuarioId AND ud.documento_id = :documentoId", nativeQuery = true)
	boolean existsRelationship(@Param("usuarioId") Long usuarioId, @Param("documentoId") Long documentoId);

}