package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoSecudarioModel;

@Repository
public interface ProcessoSecudarioRepository extends JpaRepository<ProcessoSecudarioModel, Long> {
	

	@Query("SELECT p FROM ProcessoSecudarioModel p WHERE p.procPrincipal.procId = :id")
	List<ProcessoSecudarioModel> listChildrens(@Param("id") Long id);
	//docSEI

	@Query("SELECT p FROM ProcessoSecudarioModel p WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(p.procNumero) LIKE %:keyword%)")
	List<ProcessoSecudarioModel> list(String keyword);

}
