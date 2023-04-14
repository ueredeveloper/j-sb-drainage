package com.api.main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.ProcessoModel;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, UUID> {

}
