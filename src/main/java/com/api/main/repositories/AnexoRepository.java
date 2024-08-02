package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;

@Repository
public interface AnexoRepository extends JpaRepository<AnexoModel, Long> {
	

	@Query("SELECT DISTINCT a FROM AnexoModel a LEFT JOIN a.processos p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(a.numero) LIKE %:keyword%)")
	List<AnexoModel> listByKeyword(String keyword);


}
