package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoModel;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {
	
	

}
