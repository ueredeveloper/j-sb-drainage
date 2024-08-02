package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
	
	
	

	@Query("SELECT d FROM DocumentoModel d "
		    + "LEFT JOIN d.processo p "
		    + "LEFT JOIN d.endereco e "
		    + "LEFT JOIN p.anexo a "
		    + "WHERE "
		    + "LOWER(d.numero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(d.numeroSei) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(p.numero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(a.numero) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<DocumentoModel> listByKeyword (@Param("keyword") String keyword);

	
		
}