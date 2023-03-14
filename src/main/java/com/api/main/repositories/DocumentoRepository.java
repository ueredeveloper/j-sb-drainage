package com.api.main.repositories;

import com.api.main.models.DocumentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, UUID > {
  
}