package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoOutorgaModel;


@Repository
public interface TipoOutorgaRepository extends JpaRepository<TipoOutorgaModel, Long> {

}
