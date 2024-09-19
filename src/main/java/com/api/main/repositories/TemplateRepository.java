package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.TemplateModel;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateModel, Long> {
	
	@Query("SELECT i FROM TemplateModel i "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<TemplateModel> listByKeyword(@Param("keyword") String keyword);
}
