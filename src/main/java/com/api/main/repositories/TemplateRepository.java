package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;
import com.api.main.models.TemplateModel;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateModel, Long> {

}
