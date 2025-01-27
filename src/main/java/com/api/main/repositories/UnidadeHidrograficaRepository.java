package com.api.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.AnexoModel;
import com.api.main.models.UnidadeHidrograficaModel;

@Repository
public interface UnidadeHidrograficaRepository extends JpaRepository<UnidadeHidrograficaModel, Long> {

	@Query(value = "SELECT * FROM find_uh_by_point(:lng, :lat)", nativeQuery = true)
	    List<Object[]> findByPoint(@Param("lat") Double lat, @Param("lng") Double lng);

	    @Query(value = "SELECT objectid, uh_codigo, uh_label, bacia_codi, uh_nome FROM unidades_hidrograficas", nativeQuery = true)
	    List<Object[]> listAll();

}
