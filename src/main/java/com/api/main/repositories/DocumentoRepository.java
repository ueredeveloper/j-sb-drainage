package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {

}