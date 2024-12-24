-- Seleciona interferencia e finalidades, caso não haja finalidades seta array vazia [].

select concat (
'{', '"interferencia"', ':', _s.id,','
'"finalidades"', ':',
CASE WHEN EXISTS (SELECT * FROM finalidade _f WHERE _f.interferencia = _s.id) 
THEN CONCAT('[', (SELECT STRING_AGG(CONCAT(
	'{', '"id"', ':', _f.id, ',', 
	'"finalidade"', ':', '"', _f.finalidade, '"', ',',
	'"subfinalidade"', ':', '"', _f.subfinalidade, '"', ',',
	'"quantidade"', ':', '"', _f.quantidade, '"', ',',
	'"consumo"', ':', '"', _f.consumo, '"', ',',
	'"total"', ':', '"', _f.total, '"', 
	'}'), ',')
FROM finalidade _f WHERE _f.interferencia = _s.id), ']') 
ELSE '[]' END, 
'}')
from subterranea _s
where _s.id = 11



rascunhos para o java 

		    +  "'\"finalidades\"', ':', '[', "
		    +  "COALESCE(STRING_AGG(DISTINCT CONCAT(" 
		    +  "'{', '\"id\"', ':' , '\"', _f.id,'\"' , ',',"
		    +  "'\"tipoFinalidade\"', ':' , '{\"id\":\"', _f.tipo_finalidade, '\"}',',',"
		    +  "'\"finalidade\"', ':' , '\"', _f.finalidade,'\"' , ',',"
		    +  "'\"subfinalidade\"', ':' , '\"', _f.subfinalidade,'\"' , ',',"
		    +  "'\"quantidade\"', ':' , '\"', COALESCE(_f.quantidade, 0),'\"' , ',',"
		    +  "'\"consumo\"', ':' , '\"', COALESCE(_f.consumo, 0),'\"' , ',',"
		    +  "'\"total\"', ':' , '\"', COALESCE(_f.total, 0),'\"' , '}'"
		    + "), ',') , '')"
		    +  ",'],', "
		    +  "'\"demandas\"', ':', '[', "
			+  "COALESCE(STRING_AGG(DISTINCT CONCAT(" 
			+  "'{', '\"id\"', ':' , '\"', _d.id,'\"' , ',',"
			+  "'\"tipoFinalidade\"', ':' , '{\"id\":\"', _d.tipo_finalidade, '\"}',',',"
			+  "'\"mes\"', ':' , '\"', _d.mes,'\"' , ',',"
			+  "'\"periodo\"', ':' , '\"', _d.periodo,'\"' , ',',"
			+  "'\"tempo\"', ':' , '\"', _d.tempo,'\"' , ',',"
			//+  "'\"interferencia\"', ':' , '{\"id\":\"', _d.interferencia, '\"}',',',"
			+  "'\"vazao\"', ':' , '\"', COALESCE(_d.vazao, 0),'\"' , '}'"
			+ "), ',') , '')"



+ "'\"finalidades\"', ':', '[' || COALESCE(STRING_AGG(DISTINCT CONCAT("
			+ "'{', '\"id\"', ':' , CASE WHEN _d.id IS NOT NULL THEN _f.id ELSE '0' END,  ',',"
			+ "'\"tipoFinalidade\"', ':' , '{\"id\":\"', COALESCE(_f.tipo_finalidade, 0), '\"}',',',"
			+ "'\"finalidade\"', ':' , '\"', _f.finalidade,'\"' , ',',"
			+ "'\"subfinalidade\"', ':' , '\"', _f.subfinalidade,'\"' , ',',"
			+ "'\"quantidade\"', ':' , '\"', COALESCE(_f.quantidade, 0),'\"' , ',', "
			+ "'\"consumo\"', ':' , '\"', COALESCE(_f.consumo, 0),'\"' , ',', "
			+ "'\"total\"', ':' , '\"', COALESCE(_f.total, 0),'\"' , '}' "
			+ "), ','), '') || ']', ',', "
		    
			+ "'\"demandas\"', ':', '[' || COALESCE(STRING_AGG(DISTINCT CONCAT("
			+ "'{', '\"id\"', ':' , CASE WHEN _d.id IS NOT NULL THEN _d.id ELSE '0' END, ',', "
			+ "'\"tipoFinalidade\"', ':' , '{\"id\":\"', COALESCE(_d.tipo_finalidade, 0), '\"}',',', "
			+ "'\"mes\"', ':' , '\"', COALESCE(_d.mes, 0),'\"' , ',', "
			+ "'\"periodo\"', ':' , '\"', COALESCE(_d.periodo, 0),'\"' , ',',"
			+ "'\"tempo\"', ':' , '\"', COALESCE(_d.tempo, 0),'\"' , ',', "
			+ "'\"vazao\"', ':' , '\"', COALESCE(_d.vazao, 0),'\"' , '}'"
			+ "), ','), '') || ']'" 




