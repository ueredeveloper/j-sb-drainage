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
		    + "LEFT JOIN d.docProcesso p "
		    + "LEFT JOIN d.docEndereco e "
		    + "LEFT JOIN p.anexo a "
		    + "WHERE "
		    + "LOWER(d.docNumero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(d.docSei) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(p.procNumero) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(e.endLogradouro) LIKE LOWER(CONCAT('%', :keyword, '%')) "
		    + "OR LOWER(a.numero) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<DocumentoModel> listByKeyword (@Param("keyword") String keyword);

	
		
}