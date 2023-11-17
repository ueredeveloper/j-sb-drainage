package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DocumentoModel;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
		@Query("SELECT d FROM DocumentoModel d " +
		       "WHERE d.docNumero LIKE %:keyword% " +
		       //"OR d.doc_processo LIKE %:keyword% " +
		       "OR d.docSEI LIKE %:keyword%")
		List<DocumentoModel> search(@Param("keyword") String keyword);
		
}