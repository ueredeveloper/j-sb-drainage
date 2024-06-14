package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.FinalidadeAutorizadaModel;

@Repository
public interface FinalidadeAutorizadaRepository extends JpaRepository<FinalidadeAutorizadaModel, Long> {
	

	@Query("SELECT f FROM FinalidadeAutorizadaModel f")
	List<FinalidadeAutorizadaModel> list(@Param("keyword") String keyword);
}
