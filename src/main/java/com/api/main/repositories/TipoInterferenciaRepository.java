package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.main.models.TipoInterferenciaModel;

@Repository
public interface TipoInterferenciaRepository extends JpaRepository<TipoInterferenciaModel, Long> {

	@Query("SELECT a FROM TipoInterferenciaModel a ")
	List<TipoInterferenciaModel> listAll();
}
