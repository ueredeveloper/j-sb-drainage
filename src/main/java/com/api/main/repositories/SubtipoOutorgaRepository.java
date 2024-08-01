package com.api.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.SubtipoOutorgaModel;

@Repository
public interface SubtipoOutorgaRepository extends JpaRepository<SubtipoOutorgaModel, Long> {

}
