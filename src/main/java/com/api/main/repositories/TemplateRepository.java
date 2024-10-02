package com.api.main.repositories;

import java.util.List;
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
			+ "WHERE (:tipoDocumento IS NULL OR :tipoDocumento = '' OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
			+ "AND (:tipoOutorga IS NULL OR :tipoOutorga = '' OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :param2, '%'))) "
			+ "AND (:subtipoOutorga IS NULL OR :subtipoOutorga = '' OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :param3, '%')))")
	Set<TemplateModel> listTemplatesByParams(@Param("keyword") String tipoDocumento,
			@Param("param2") String tipoOutorga, @Param("param3") String subtipoOutorga);

}
