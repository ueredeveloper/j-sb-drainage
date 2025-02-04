
SELECT
_p.numero, 
  _u.id us_id, 
  _u.nome us_nome,
  _u.cpf_cnpj us_cpf_cnpj, 
  _ud.documento_id us_doc_id,

  _d.endereco AS doc_end, 
  _d.numero_sei AS doc_sei,
  _d.processo AS proc_sei,
  _e.id AS end_id, 
  _e.logradouro end_logradouro

  FROM usuario _u
  LEFT JOIN usuario_documento _ud ON _ud.usuario_id = _u.id
  LEFT JOIN documento _d ON _d.id = _ud.documento_id
  LEFT JOIN processo _p ON _p.id = _d.processo
  LEFT JOIN endereco _e ON _e.id = _d.endereco
  where 
  	_u.nome LIKE '%456.123%' 
	 OR _u.cpf_cnpj::text LIKE '%456.123%' 
		OR _d.numero_sei::text LIKE '%456.123%' 
		  OR _p.numero LIKE '%456.123%' 