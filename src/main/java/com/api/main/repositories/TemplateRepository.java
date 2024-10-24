package com.api.main.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.TemplateModel;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateModel, Long> {

	@Query("SELECT i FROM TemplateModel i "
			+ "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	Set<TemplateModel> listByKeyword(@Param("keyword") String keyword);

	@Query("SELECT i FROM TemplateModel i "
	        + "WHERE ((:tipoDocumento IS NULL OR :tipoDocumento = '' OR LOWER(i.nome) LIKE LOWER(CONCAT('%', :tipoDocumento, '%'))) "
	        + "AND (:tipoOutorga IS NULL OR :tipoOutorga = '' OR LOWER(i.nome) LIKE LOWER(CONCAT('%', :tipoOutorga, '%'))) "
	        + "AND (:subtipoOutorga IS NULL OR :subtipoOutorga = '' OR LOWER(i.nome) LIKE LOWER(CONCAT('%', :subtipoOutorga, '%')))) "
	        // Add shared folders conditions
	        + "OR (i.diretorio LIKE '%utils%' OR i.diretorio LIKE '%models%' OR i.diretorio LIKE '%shared%')")
	Set<TemplateModel> listTemplatesByParams(
	        @Param("tipoDocumento") String tipoDocumento,
	        @Param("tipoOutorga") String tipoOutorga, 
	        @Param("subtipoOutorga") String subtipoOutorga);


}
