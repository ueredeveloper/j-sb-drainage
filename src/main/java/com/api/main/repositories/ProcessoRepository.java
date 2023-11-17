package com.api.main.repositories;

import java.util.List;

import com.api.main.models.DocumentoModel;
import com.api.main.models.ProcessoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {
	
	@Query(value = "select "
			+ "* "
			+ "from processo as pro "
			+ "where pro.procPrincipal = :procPrincipal ", 
			nativeQuery = true)
	List<ProcessoModel> findChildrens (@Param("procPrincipal") Long procPrincipal);
	
	@Query("SELECT p FROM ProcessoModel p " +
		       "WHERE p.procNumero LIKE %:keyword% ")
		List<ProcessoModel> search(@Param("keyword") String keyword);
	
	

}
