package com.api.main.repositories;

import com.api.main.models.DocumentoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoModel, Long> {
		@Query("SELECT d FROM DocumentoModel d " +
		       "WHERE d.doc_numero LIKE %:keyword% " +
		       "OR d.doc_processo LIKE %:keyword% " +
		       "OR d.doc_sei LIKE %:keyword%")
		List<DocumentoModel> searchDocuments(@Param("keyword") String keyword);
}