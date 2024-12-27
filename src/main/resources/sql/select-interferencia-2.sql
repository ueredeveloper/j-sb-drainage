SELECT CONCAT('{', '"interferencia"', ':', '{', '"id"', ':', _i.id, ',', '"latitude"', ':', '"', 
COALESCE(_i.latitude, null), '"',
 ',', '"longitude"', ':', '"', 
COALESCE(_i.longitude, null), '"', ',' ,'"vazaoOutorgavel"', ':', 
COALESCE(_s.vazao_outorgavel, 0), ',' ,'"vazaoTeste"', ':', 
COALESCE(_s.vazao_teste, 0), ',' ,'"vazaoSistema"', ':', 
COALESCE(_s.vazao_sistema, 0), ',' ,
'"profundidade"', ':', '"', COALESCE(NULLIF(_s.profundidade, null), 'default_value'), '"', ',', 
'"nivelEstatico"', ':', '"', COALESCE(NULLIF(_s.nivel_estatico, null), 'default_value'), '"', ',', 
'"nivelDinamico"', ':', '"', COALESCE(NULLIF(_s.nivel_dinamico, null), 'default_value'), '"', ',', '"caesb"', ':', 
COALESCE(_s.caesb, 'false'), ',' ,'"tipoPoco"', ':', 
COALESCE(CONCAT('{', '"id"', ':', _s.tipo_poco, '}'), 'null'), ',', '"endereco"', ':', 
COALESCE(CONCAT('{', '"id"', ':', _e.id, ',', '"logradouro"', ':', '"', _e.logradouro, '"', '}'), 'null'), ',', '"tipoInterferencia"', ':', 
CASE WHEN _ti.id 
IS NOT NULL THEN CONCAT('{', '"id"', ':', _ti.id, ',', '"descricao"', ':', '"', _ti.descricao, '"', '}') ELSE 'null' END, ',', '"tipoOut
orga"', ':', 
CASE WHEN _to.id IS NOT NULL THEN CONCAT('{', '"id"', ':', _to.id, ',', '"descricao"', ':', '"', _to.descricao, '"', '}') ELSE '
null' END, ',', '"subtipoOutorga"', ':', 
CASE WHEN _so.id IS NOT NULL THEN CONCAT('{', '"id"', ':', _so.id, ',', '"descricao"', ':', '"', _so
.descricao, '"', '}') ELSE 'null' END, ',', '"situacaoProcesso"', ':', 
CASE WHEN _sp.id IS NOT NULL THEN CONCAT('{', '"id"', ':', _sp.id, ','
, '"descricao"', ':', '"', _sp.descricao, '"', '}') ELSE 'null' END, ',', '"tipoAto"', ':', 
CASE WHEN _ta.id IS NOT NULL THEN CONCAT('{', '"id"', ':', _ta.id, ',', '"descricao"', ':', '"', _ta.descricao, '"', '}') 
ELSE 'null' END, ',', '"finalidades"', ':', '[', 
COALESCE(STRING_AGG(
DISTINCT CONCAT('{', '"id"', ':' , '"', _f.id,'"' , ',','"tipoFinalidade"', ':' , '{"id":"', _f.tipo_finalidade, '"}',',','"finalidade"', ':
' , '"', _f.finalidade,'"' , ',','"subfinalidade"', ':' , '"', _f.subfinalidade,'"' , ',','"quantidade"', ':' , '"', _f.quantidade,'"' , ',',
'"consumo"', ':' , '"', _f.consumo,'"' , ',','"total"', ':' , '"', _f.total,'"' , '}'), ',') , ''),'],', '"demandas"', ':', '[', 
COALESCE(STRING_AGG(DISTINCT CONCAT('{', '"id"', ':' , '"', _d.id,'"' , ',','"tipoFinalidade"', ':' , '{"id":"', _d.tipo_finalidade, '"}',',','"mes"', ':
' , '"', _d.mes,'"' , ',','"periodo"', ':' , '"', _d.periodo,'"' , ',','"tempo"', ':' , '"', _d.tempo,'"' , ',','"vazao"', ':' , '"', 
_d.vazao,'"' , '}'), ',') , ''),']', '}}') 
FROM interferencia _i LEFT JOIN finalidade _f ON _f.interferencia = _i.id 
LEFT JOIN demanda _d ON _d.interferencia = _i.id LEFT JOIN tipo_interferencia _ti ON _ti.id = _i.tipo_interferencia 
LEFT JOIN tipo_outorga _to ON _to.id = _i.tipo_outorga LEFT JOIN subtipo_outorga _so ON _so.id = _i.subtipo_outorga 
LEFT JOIN situacao_processo _sp ON _sp.id = _i.situacao_processo 
LEFT JOIN tipo_ato _ta ON _ta.id = _i.tipo_ato LEFT JOIN endereco _e ON _e.id = _i.endereco 
LEFT JOIN subterranea _s ON _i.id = _s.id 
WHERE _e.logradouro LIKE '%%'
 GROUP BY _i.id,_i.latitude,_i.longitude,_e.id,_e.logradouro,_s.vazao_outorgavel,_s.vazao_teste,_s.vazao_sistema,
 _s.profundidade,_s.nivel_estatico,_s.nivel_dinamico,_s.caesb,_s.tipo_poco,_ti.id,_to.id,_so.id,_sp.id,_ta.id