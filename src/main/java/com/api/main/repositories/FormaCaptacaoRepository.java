package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.FormaCaptacaoModel;

@Repository
public interface FormaCaptacaoRepository extends JpaRepository<FormaCaptacaoModel, Long> {

}
