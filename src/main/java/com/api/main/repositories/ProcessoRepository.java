package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoModel;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {
	

	@Query("SELECT p FROM ProcessoModel p WHERE p.procPrincipal.procId = :id")
	List<ProcessoModel> listChildrens(@Param("id") Long id);

	@Query("SELECT p FROM ProcessoModel p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(p.procNumero) LIKE %:keyword%)")
	List<ProcessoModel> list(String keyword);

}
