package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoPocoModel;

@Repository
public interface TipoPocoRepository extends JpaRepository<TipoPocoModel, Long> {

}
