package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.InterferenciaModel;

@Repository
public interface InterferenciaRepository extends JpaRepository<InterferenciaModel, Long> {

	@Query("SELECT i FROM InterferenciaModel i")
	List<InterferenciaModel> list(@Param("keyword") String keyword);

}
