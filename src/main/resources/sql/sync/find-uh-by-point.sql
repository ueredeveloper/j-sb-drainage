/***
* Busca unidade hidrográfica a partir de um ponto (lat, lng).
*/
CREATE OR REPLACE FUNCTION find_uh_by_point(longitude DOUBLE PRECISION, latitude DOUBLE PRECISION)
RETURNS TABLE (objectid INTEGER, uh_codigo INTEGER, uh_label TEXT, bacia_codi TEXT, uh_nome TEXT ) AS $$
BEGIN
    RETURN QUERY
    SELECT 
	 	uh.objectid::INTEGER,
		uh.uh_codigo::INTEGER,
		uh.uh_label::TEXT,
		uh.bacia_codi::TEXT, 
        uh.uh_nome::TEXT
		
    FROM unidades_hidrograficas uh
    WHERE ST_Contains(uh.shape, ST_SetSRID(ST_MakePoint(longitude, latitude), 4674));
END;
$$ LANGUAGE plpgsql;



--DROP FUNCTION find_uh_by_point(DOUBLE PRECISION, DOUBLE PRECISION);


select find_uh_by_point( -15.8722731, -47.9164122)