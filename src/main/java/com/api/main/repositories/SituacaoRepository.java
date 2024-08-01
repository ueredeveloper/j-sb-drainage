package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.SituacaoModel;

@Repository
public interface SituacaoRepository extends JpaRepository<SituacaoModel, Long> {

}
