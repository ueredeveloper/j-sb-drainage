package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.EnderecoModel;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
	
	

	@Query("SELECT e FROM EnderecoModel e WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<EnderecoModel> listByKeyword(@Param("keyword") String keyword);

}
