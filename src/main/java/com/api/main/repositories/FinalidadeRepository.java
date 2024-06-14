package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.FinalidadeModel;

@Repository
public interface FinalidadeRepository extends JpaRepository<FinalidadeModel, Long> {
	

	@Query("SELECT f FROM FinalidadeModel f")
	List<FinalidadeModel> list(@Param("keyword") String keyword);

}
