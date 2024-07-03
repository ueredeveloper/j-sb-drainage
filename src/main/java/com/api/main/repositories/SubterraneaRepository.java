package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.SubterraneaModel;

@Repository
public interface SubterraneaRepository extends JpaRepository<SubterraneaModel, Long> {
	
	// O método não está pronto, verificar InterferenciaRepository
	@Query("SELECT s FROM SubterraneaModel s")
	List<SubterraneaModel> listByKeword(@Param("keyword") String keyword);

}
