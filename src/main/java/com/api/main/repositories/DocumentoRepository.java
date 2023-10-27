package com.api.main.repositories;

import com.api.main.models.DocumentoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {

}