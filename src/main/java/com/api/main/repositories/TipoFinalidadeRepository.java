package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoFinalidadeModel;

@Repository
public interface TipoFinalidadeRepository extends JpaRepository<TipoFinalidadeModel, Long> {

}