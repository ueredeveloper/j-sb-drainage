package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoAtoModel;



@Repository
public interface TipoAtoRepository extends JpaRepository<TipoAtoModel, Long> {

}