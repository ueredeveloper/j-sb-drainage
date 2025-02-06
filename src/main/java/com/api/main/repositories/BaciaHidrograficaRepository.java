package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.BaciaHidrograficaModel;


@Repository
public interface BaciaHidrograficaRepository extends JpaRepository<BaciaHidrograficaModel, Long> {

	
	@Query(value = "SELECT * FROM find_bh_by_point(:lng, :lat)", nativeQuery = true)
	List<Object[]> findByPoint(@Param("lat") Double lat, @Param("lng") Double lng);

	@Query(value = "SELECT objectid, bacia_nome, bacia_cod, ST_AsGeoJSON(shape) AS shape FROM bacias_hidrograficas", nativeQuery = true)
	List<Object[]> listAll();

}
