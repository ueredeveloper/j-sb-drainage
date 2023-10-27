package com.api.main.repositories;

import java.util.List;

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
			+ "where pro.proc_processo_principal = :proc_processo_principal ", 
			nativeQuery = true)
	List<ProcessoModel> findProcessosSecundarios (@Param("proc_processo_principal") Long proc_processo_principal);
	
	

}
