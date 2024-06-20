package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;

@Repository
public interface AnexoRepository extends JpaRepository<AnexoModel, Long> {
	
	
	@Query("SELECT obj FROM AnexoModel obj WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(obj.numero) LIKE %:keyword%)")
	List<AnexoModel> listByKeyword (String keyword);

	/*@Query("SELECT a FROM AnexoModel a WHERE a.anPrincipal.procId = :id")
	List<AnexoModel> listAnexos(@Param("id") Long id);
	//docSEI

	@Query("SELECT a FROM AnexoModel a WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(a.anNumero) LIKE %:keyword%)")
	List<AnexoModel> list(String keyword);*/

}
