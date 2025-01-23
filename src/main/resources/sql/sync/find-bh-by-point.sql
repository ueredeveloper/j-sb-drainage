/*
 * Busca bacia hidrográfica a partir de um ponto (lat, lng)
 */

CREATE OR REPLACE FUNCTION find_bh_by_point(longitude DOUBLE PRECISION, latitude DOUBLE PRECISION)
RETURNS TABLE (bacia_nome TEXT, bacia_cod TEXT, objectid INTEGER) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        bh.bacia_nome::TEXT, 
        bh.bacia_cod::TEXT, 
        bh.objectid::INTEGER
    FROM bacias_hidrograficas bh
    WHERE ST_Contains(bh.shape, ST_SetSRID(ST_MakePoint(longitude, latitude), 4674));
END;
$$ LANGUAGE plpgsql;



DROP FUNCTION find_bh_by_point(DOUBLE PRECISION, DOUBLE PRECISION);