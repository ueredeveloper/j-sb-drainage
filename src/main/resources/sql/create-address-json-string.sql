/** Seleciona endereço em formato json string 
	Funções: 
		COALESCE: Verifica se o valor é  nulo. Se nulo preenche com string vazia, ex: "".*/

/** Seleciona endereço em formato json string */

select concat(
'{', 
'"', 'logradouro', '"', ':', '"', COALESCE(_e.logradouro, '') ,'"',',', 
'"', 'bairro', '"', ':', '"', COALESCE(_e.bairro, '')  ,'"',',', 
'"', 'cep', '"', ':', '"', COALESCE(_e.cep, '')  ,'"',',', 
'"', 'cidade', '"', ':', '"', COALESCE(_e.cidade, '')  ,'"',',', 
'"', 'estado', '"', ':', 
'{', '"', 'id', '"', ':', COALESCE(_es.id, null)  ,',', '"', 'descricao', '"', ':', '"', _es.descricao, '"','}'
'}')
from endereco _e
left join estado _es on _es.id = _e.estado

-- Se preciso adicionar um Estado a um Endereço
update endereco e set e.estado = 3 where e.id = 1
/*
Em formato @Query: 


	@Query("SELECT " +
		       "CONCAT('{', '\"endereco\"', ':', '{', " +
		       "'\"id\"', ':', '\"', CAST(e.id AS string), '\"', ',', " +
		       "'\"logradouro\"', ':','\"', COALESCE(e.logradouro, ''),'\"', ',', " +
		       "'\"cidade\"', ':','\"', COALESCE(e.cidade, ''),'\"', ',', " +
		       "'\"bairro\"', ':','\"', COALESCE(e.bairro, ''),'\"', ',', " +
		       "'\"cep\"', ':','\"', COALESCE(e.cep, ''),'\"', ',', " +
		       "'\"estado\"', ':','{', '\"id\"', ':', '\"', " +
		       "COALESCE(CAST(es.id AS string), ''), '\"', ',', " +
		       "'\"descricao\"', ':', '\"', COALESCE(es.descricao, ''), '\"', '}'" +
		       ",'}}') " +
		       "FROM EnderecoModel e " +
		       "LEFT JOIN e.estado es " +
		       "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :keyword, '%')))")
		List<Object> listByKeyword(@Param("keyword") String keyword);
	
	*/
	
	