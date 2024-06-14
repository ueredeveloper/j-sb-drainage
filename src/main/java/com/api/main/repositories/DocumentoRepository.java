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
			+ "LEFT JOIN ProcessoModel p ON p.procId = d.docProcesso "
			+ "LEFT JOIN EnderecoModel e ON e.endId = d.docEndereco "
			+ "WHERE "
			+ "LOWER (d.docNumero) LIKE LOWER(concat('%', :keyword, '%')) "
			+ "OR LOWER (d.docSei) LIKE LOWER(concat('%', :keyword, '%')) "
			+ "OR LOWER (p.procNumero) LIKE LOWER(concat('%', :keyword, '%')) "
			+ "OR LOWER(e.endLogradouro) LIKE LOWER(concat('%', :keyword, '%'))")
		List<DocumentoModel> list(@Param("keyword") String keyword);
	
		
}