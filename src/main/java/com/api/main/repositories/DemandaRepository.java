package com.api.main.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.main.models.DemandaModel;

@Repository
public interface DemandaRepository extends JpaRepository<DemandaModel, Long> {

	Optional<DemandaModel> findById(Double id);
	
	@Query(value = "SELECT " +
            "    _i.id AS int_id, " +
            "    _e.id AS end_id, " +
            "    _e.logradouro AS end_logradouro, " +
            "    _i.latitude AS int_latitude, " +
            "    _i.longitude AS int_longitude, " +
            "    _s.tipo_poco AS sub_tp_id, " +
            "    json_agg( " +
            "        json_build_object( " +
            "            'id_interferencia', _d.interferencia, " +
            "            'vazao_lh', _d.vazao, " +
            "            'vazao_ld', _d.vazao * _d.tempo, " +
            "            'mes', _d.mes, " +
            "            'vazao_mh', _d.vazao / 1000, " +
            "            'tempo_h', _d.tempo, " +
            "            'vol_max_md', (_d.vazao / 1000) * _d.tempo, " +
            "            'periodo_d', _d.periodo, " +
            "            'vol_mensal_mm', (_d.vazao / 1000) * _d.periodo * _d.tempo " +
            "        ) " +
            "    ) AS dt_demanda, " +
            "    (SELECT COALESCE(SUM((__d.vazao / 1000) * __d.periodo * __d.tempo), 0) " +
            "     FROM demanda __d " +
            "     WHERE __d.interferencia = _i.id AND __d.tipo_finalidade = 2) AS vol_anual_ma " +
            "FROM interferencia _i " +
            "JOIN subterranea _s ON _s.id = _i.id " +
            "JOIN endereco _e ON _e.id = _i.endereco " +
            "JOIN demanda _d ON _d.interferencia = _i.id " +
            "WHERE _e.id = :endId AND _d.tipo_finalidade = 2 " +
            "GROUP BY _i.id, _e.id, _e.logradouro, _i.latitude, _i.longitude, _s.tipo_poco", 
            nativeQuery = true)
	Set<Object[]> listByAddressId(@Param("endId") Long endId);

}