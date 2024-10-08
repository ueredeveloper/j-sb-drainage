-- Cria uma string em formato json através de uma seleção de interferências.
-- Query para h2 ou sql server

SELECT 
    CONCAT(
        '{', '"', 'finalidades', '"', ':', '[', STRING_AGG( CONCAT('{', '"', 'finalidade', '"', ':', f.finalidade, '}'), ','), 
        
        ']}'
    ) AS finalidades
FROM interferencia i
LEFT JOIN finalidade f


SELECT 
    CONCAT(
        '{', '"', 'finalidades', '"', ':', '[', STRING_AGG( DISTINCT CONCAT('{', '"', 'finalidade', '"', ':', f.finalidade, '}'), ','), 
        ']}'
    ) AS finalidades, 
    CONCAT(
        '{', '"', 'demandas', '"', ':', '[', STRING_AGG( DISTINCT CONCAT('{', '"', 'tipoFinalidade', '"', ':', d.tipo_finalidade, '"', 'periodo', '"', ':', '"', d.periodo, '"', '}'), ','), 
        ']}'
    ) AS demandas
FROM interferencia i
LEFT JOIN finalidade f ON f.interferencia = i.id
LEFT JOIN demanda d ON d.interferencia = i.id
LEFT JOIN tipo_finalidade tf ON tf.id = d.tipo_finalidade



@Query(value = "SELECT " +
            "CONCAT('{', " +
            "'\"finalidades\":', '[', " +
            "STRING_AGG(DISTINCT CONCAT('{', " +
            "'\"id\":', f.id, ',', " +
            "'\"finalidade\":', '\"', f.finalidade, '\"', ',', " +
            "'\"tipoFinalidade\":', '{', " +
            "'\"id\":', f.tipo_finalidade,'}'), ',') , " +
            "']}' " +
            ") AS finalidades, " +
            "CONCAT('{', " +
            "'\"demandas\":', '[', " +
            "STRING_AGG(DISTINCT CONCAT('{', " +
            "'\"id\":', d.id, ',', " +
            "'\"tipoFinalidade\":', '{', " +
            "'\"id\":', d.tipo_finalidade, " +
            "'},', " +
            "'\"vazao\":', '\"', d.vazao, '\"', ',', " +
            "'\"tempo\":', '\"', d.tempo, '\"', ',', " +
            "'\"periodo\":', '\"', d.periodo, '\"', ',', " +
            "'\"mes\":', '\"', d.mes, '\"', " +
            "'}'), ',') , " +
            "']}' " +
            ") AS demandas " +
            "FROM interferencia i " +
            "LEFT JOIN finalidade f ON f.interferencia = i.id " +
            "LEFT JOIN demanda d ON d.interferencia = i.id " +
            "LEFT JOIN endereco e ON e.id = i.endereco " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))", 
   nativeQuery = true)
Set<String> listByLogradouro(@Param("keyword") String keyword);