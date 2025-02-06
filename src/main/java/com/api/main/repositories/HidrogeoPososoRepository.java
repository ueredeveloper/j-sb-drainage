package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.HidrogeoPoroso;


@Repository
public interface HidrogeoPososoRepository extends JpaRepository<HidrogeoPoroso, Long> {

	@Query(value = "SELECT * FROM find_poroso_by_point(:lng, :lat)", nativeQuery = true)
	List<Object[]> findByPoint(@Param("lat") Double lat, @Param("lng") Double lng);

	@Query(value = "SELECT objectid, cod_plan, sistema, q_media FROM hidrogeo_poroso", nativeQuery = true)
	List<Object[]> listAll();
	
	@Query(value = "SELECT objectid, cod_plan, sistema, q_media FROM hidrogeo_poroso WHERE cod_plan =:codPlan", nativeQuery = true)
	List<Object[]> listByCodPlan(@Param("codPlan") String codPlan);
	

}
