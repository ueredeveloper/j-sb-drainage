package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoDocumentoModel;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoModel, Long> {
	
		
}