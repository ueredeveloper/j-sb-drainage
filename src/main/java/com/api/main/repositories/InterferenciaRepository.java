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
	List<InterferenciaModel> listByKeword(@Param("keyword") String keyword);

	// Consulta para buscar interferências pelo logradouro do endereço
	// O h2 não tem uma função de pesquisar com ou sem acento, o postgress sim, fazer no banco postgress ou adicionar função no h2
    @Query("SELECT i FROM InterferenciaModel i " + 
           "LEFT JOIN i.endereco e " +
           "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<InterferenciaModel> listByLogradouro(@Param("keyword") String keyword);


}
