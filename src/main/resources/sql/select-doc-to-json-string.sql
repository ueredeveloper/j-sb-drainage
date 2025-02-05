--select * from processo
--update processo p set anexo = null where p.id = 43
--select * from documento
-- Testando se não houver outros relacionamentos, se traz o objeto null
--update documento d set processo = null where d.id = 89
--update documento d set endereco = null where d.id = 89
--update documento d set tipo_documento = null where d.id = 89

--select * from documento -> 11 documentos



SELECT CONCAT (
'{', 
'"documento"', ':', 
'{'
'"id"',':','"', _d.id,'"',',',
'"numero"',':','"', _d.numero,'"',',',
'"numeroSei"',':','"', _d.numero_sei,'"',',',

'"endereco"', ':',
CASE WHEN _e.id IS NOT NULL
THEN CONCAT('{', 
'"','id','"',':','"',_e.id,'"',',',
'"','logradouro','"',':','"',_e.logradouro,'"',
'}'
)
ELSE 'null'
END, ','
'"', 'tipoDocumento', '"', ':',
CASE WHEN _td.id IS NOT NULL
THEN CONCAT('{', 
'"','id','"',':','"',_td.id,'"',',',
'"','descricao','"',':','"',_td.descricao,'"',
'}'
)
ELSE 'null'
END, ','

'"', 'processo', '"', ':',
CASE WHEN _p.id IS NOT NULL
THEN CONCAT('{', 
'"','id','"',':','"',_p.id,'"',',',
'"','numero','"',':','"',_p.numero,'"',',',
'"','anexo','"',':',
CASE WHEN _a.id IS NOT NULL
THEN CONCAT('{', 
'"','id','"',':','"',_a.id,'"',',',
'"','numero','"',':','"',_a.numero,'"',
'}'
)
ELSE 'null'
END, 

'}'

)
ELSE 'null'
END


,'}'
,'}'
)
from documento _d
LEFT OUTER JOIN endereco _e on _e.id = _d.endereco
LEFT OUTER JOIN documento_tipo _td on _td.id = _d.tipo_documento
LEFT OUTER JOIN processo _p on _p.id = _d.processo
LEFT OUTER JOIN anexo _a on _p.anexo = _a.id
WHERE _d.numero like '%%'