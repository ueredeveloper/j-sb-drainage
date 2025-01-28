package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.SituacaoProcessoModel;


@Repository
public interface SituacaoProcessoRepository extends JpaRepository<SituacaoProcessoModel, Long> {

}
