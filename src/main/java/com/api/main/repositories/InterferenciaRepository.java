package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaModel;

@Repository
public interface InterferenciaRepository extends JpaRepository<InterferenciaModel, Long> {

	@Query("SELECT i FROM InterferenciaModel i")
	List<InterferenciaModel> list(@Param("keyword") String keyword);

	// Consulta para buscar interferências pelo logradouro do endereço
	@Query("SELECT i " + "FROM InterferenciaModel i " + "LEFT JOIN i.interEndereco e "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(i.interEndereco) LIKE %:keyword%)")
	List<InterferenciaModel> searchInterferenciasByLogradouro(@Param("keyword") String keyword);
}
