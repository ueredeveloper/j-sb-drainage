package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.FinalidadeRequeridaModel;
import com.api.main.models.FinalidadeRequeridaModel;

@Repository
public interface FinalidadeRequeridaRepository extends JpaRepository<FinalidadeRequeridaModel, Long> {

	@Query("SELECT f FROM FinalidadeRequeridaModel f")
	List<FinalidadeRequeridaModel> list(@Param("keyword") String keyword);

}
