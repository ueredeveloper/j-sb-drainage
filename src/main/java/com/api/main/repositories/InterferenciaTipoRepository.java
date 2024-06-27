package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaTipoModel;

@Repository
public interface InterferenciaTipoRepository extends JpaRepository<InterferenciaTipoModel, Long> {

	@Query("SELECT a FROM InterferenciaTipoModel a ")
	List<InterferenciaTipoModel> listAll();
}
