-- Cria uma string em formato json através de uma seleção de interferências.
-- Query para h2 ou sql server

SELECT 
    CONCAT(
        '{', '"', 'finalidades', '"', ':', '[', STRING_AGG( CONCAT('{', '"', 'finalidade', '"', ':', f.finalidade, '}'), ','), 
        
        ']}'
    ) AS finalidades
FROM interferencia i
LEFT JOIN finalidade f