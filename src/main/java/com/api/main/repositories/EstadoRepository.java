package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.EstadoModel;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {
	
}