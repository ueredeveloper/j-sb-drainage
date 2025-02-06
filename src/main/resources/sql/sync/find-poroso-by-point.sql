/**
Busca o subsistema por uma ponto (lat, lng)
*/

--DROP FUNCTION find_poroso_by_point;


CREATE OR REPLACE FUNCTION find_poroso_by_point(longitude DOUBLE PRECISION, latitude DOUBLE PRECISION)
RETURNS TABLE (objectid INTEGER, cod_plan TEXT, sistema TEXT, q_media DOUBLE PRECISION) AS $$
BEGIN
    RETURN QUERY
    SELECT 
	 	hp.objectid::INTEGER,
		hp.cod_plan::TEXT,
		hp.sistema::TEXT,
		hp.q_media::DOUBLE PRECISION
    FROM hidrogeo_poroso hp
    WHERE ST_Contains(hp.shape, ST_SetSRID(ST_MakePoint(longitude, latitude), 4674));
END;
$$ LANGUAGE plpgsql;


-- select find_poroso_by_point( -15.8722731, -47.9164122)