SELECT 
    _i.id AS int_id, 
    _e.id AS end_id, 
    _e.logradouro AS end_logradouro, 
    _i.latitude AS int_latitude,
    _i.longitude AS int_longitude,
    _s.tipo_poco AS sub_tp_id,
    json_agg(
        json_build_object(
            'id_interferencia', _d.interferencia,
            'vazao_lh', _d.vazao,
            'vazao_ld', _d.vazao * _d.tempo,
            'mes', _d.mes,
            -- vazao_mh(m³/h) => vazao_lh/1000
            'vazao_mh', _d.vazao / 1000,
            'tempo_h', _d.tempo,
            -- vol_max_md (máx m³/dia) => vazao_lh/1000 * tempo_h
            'vol_max_md', (_d.vazao / 1000) * _d.tempo,
            'periodo_d', _d.periodo,
            -- vol_mensal_mm (m³/mês)
            'vol_mensal_mm', (_d.vazao / 1000) * _d.periodo * _d.tempo
        )
    ) AS dt_demanda, 
    (SELECT COALESCE(SUM((__d.vazao / 1000) * __d.periodo * __d.tempo), 0) 
     FROM demanda __d 
     WHERE __d.interferencia = _i.id AND __d.tipo_finalidade = 2) AS vol_anual_ma

FROM interferencia _i
JOIN subterranea _s ON _s.id = _i.id
JOIN endereco _e ON _e.id = _i.endereco
JOIN demanda _d ON _d.interferencia = _i.id
WHERE _e.id = 92 AND _d.tipo_finalidade = 2
GROUP BY _i.id, _e.id, _e.logradouro, _i.latitude, _i.longitude, _s.tipo_poco;