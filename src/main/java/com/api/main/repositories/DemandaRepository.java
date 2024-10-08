package com.api.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.main.models.DemandaModel;

@Repository
public interface DemandaRepository extends JpaRepository<DemandaModel, Long> {

	Optional<DemandaModel> findById(Double id);

}