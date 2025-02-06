/**
Busca susbsistema fraturado por pont (lat, lng)
*/
--DROP FUNCTION find_fraturado_by_point;


CREATE OR REPLACE FUNCTION find_fraturado_by_point(longitude DOUBLE PRECISION, latitude DOUBLE PRECISION)
RETURNS TABLE (objectid INTEGER, cod_plan TEXT, sistema TEXT, subsistema TEXT, vazao DOUBLE PRECISION) AS $$
BEGIN
    RETURN QUERY
    SELECT 
	 	hf.objectid::INTEGER,
		hf.cod_plan::TEXT,
		hf.sistema::TEXT,
		hf.subsistema::TEXT,
		hf.vazao::DOUBLE PRECISION
    FROM hidrogeo_fraturado hf
    WHERE ST_Contains(hf.shape, ST_SetSRID(ST_MakePoint(longitude, latitude), 4674));
END;
$$ LANGUAGE plpgsql;
