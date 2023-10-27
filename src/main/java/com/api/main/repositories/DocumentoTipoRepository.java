package com.api.main.repositories;

import com.api.main.models.DocumentoTipoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoTipoRepository extends JpaRepository<DocumentoTipoModel, Long> {
	
}