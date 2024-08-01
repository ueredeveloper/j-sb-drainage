package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	@Query("SELECT u FROM UsuarioModel u " + "WHERE " + "LOWER (u.nome) LIKE LOWER(concat('%', :keyword, '%')) ")
	List<UsuarioModel> list(@Param("keyword") String keyword);
}