/**
Busca susbsistema fraturado por pont (lat, lng)
*/
CREATE OR REPLACE FUNCTION find_fraturado_by_point(longitude DOUBLE PRECISION, latitude DOUBLE PRECISION)
RETURNS TABLE (objectid INTEGER, cod_plan TEXT, sistema TEXT, subsistema TEXT) AS $$
BEGIN
    RETURN QUERY
    SELECT 
	 	hf.objectid::INTEGER,
		hf.cod_plan::TEXT,
		hf.sistema::TEXT,
		hf.subsistema::TEXT
    FROM hidrogeo_fraturado hf
    WHERE ST_Contains(hf.shape, ST_SetSRID(ST_MakePoint(longitude, latitude), 4674));
END;
$$ LANGUAGE plpgsql;

