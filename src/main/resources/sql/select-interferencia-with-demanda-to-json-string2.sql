-- Seleciona interferencia e finalidades, caso não haja finalidades seta array vazia [].

select concat (
'{', '"interferencia"', ':', _s.id,','
'"demandas"', ':',
CASE WHEN EXISTS (SELECT * FROM finalidade _f WHERE _f.interferencia = _s.id) 
THEN CONCAT('[', (SELECT STRING_AGG(CONCAT(
	'{', '"id"', ':', _d.id, ',', 
	'"tipoFinalidade"', ':', '{', '"id"', ':',_d.tipo_finalidade, '}', ',',
	'"mes"', ':', _d.mes,',',
	'"periodo"', ':', _d.periodo,',',
	'"tempo"', ':', _d.tempo,',',
	'"vazao"', ':', _d.vazao,
	'}'), ',')
FROM demanda _d WHERE _d.interferencia = _s.id), ']') 
ELSE '[]' END, 
'}')
from subterranea _s
where _s.id = 11

