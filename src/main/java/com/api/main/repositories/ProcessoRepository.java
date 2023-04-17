package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoModel;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {
	
	@Query(value = "select "
			+ "* "
			+ "from processos as pro "
			+ "where pro.proc_principal_fk = :proc_principal_fk ", 
			nativeQuery = true)
	List<ProcessoModel> findProcessosSecundarios (@Param("proc_principal_fk") Long proc_principal_fk);
	
	

}
