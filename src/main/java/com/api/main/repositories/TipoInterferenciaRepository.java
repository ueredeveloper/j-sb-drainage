package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoInterferenciaModel;

@Repository
public interface TipoInterferenciaRepository extends JpaRepository<TipoInterferenciaModel, Long> {

}
