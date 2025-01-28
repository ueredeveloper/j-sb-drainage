package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.SubterraneaModel;


@Repository
public interface SubterraneaRepository extends JpaRepository<SubterraneaModel, Long> {

}
