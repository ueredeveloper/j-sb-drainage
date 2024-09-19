Estudo de como fugir do loop ao selectiona endereço relacionado com interferências através de uso da anotação @Query no repositório.

Como buscar endereço com latitude e longitude

```
select 
_end.logradouro logradouro, _int.latitude latit
from 
Endereco _end
left join Interferencia _int on _int.endereco = _end.id
where _end.logradouro like '%%'
```

Retorno:
```
[
    [
        "Rua Novaes Terceiro, 5555",
        -15.233333,
        -47.44444
    ],
    ...
]
```

Desta forma se houver um endereço com três interferências ele traz três objetos em uma array. Não é isso que quero.

```
[
    [
        "Rua Noaves, 1 2 3",
        -15.11,
        -47.123
    ],
    [
        "Rua Noaves, 1 2 3",
        -15.122,
        null
    ],
    [
        "Rua Noaves, 1 2 3",
        -15.1233,
        null
    ]
]
```

Página AddInterferenceController
	Preciso dos  dados
		Endereco -> id e logradouro
	    Interferencia -> 
	    	id, latitude, longitude, 
	    	tipo de interferencia (id, descricao), 
	    	tipo de outorga (id, descricao), 
	    	subtipo de outorga (id, descricao), 
	    	situcao (id, descricao), 
	    	tipo ato (id, descricao)
	    
Utilizando um projeto de teste 
https://replit.com/@ueredeveloper/j-map-json
Para adicionar Gson, depois de adicionar a dependência no maven, utilize o comando mvn dependency:copy-dependencies
para copiar a dependência para o projeto.

Melhoramento da query, adicionei o tipo de ato, mas é possível adicionar outras tabelas domínio como tipo de interferência.

```
select 
_end.id end_id, _end.logradouro logradouro, 
_int.endereco int_end_id, _int.latitude latitude, _int.longitude longitude,
_ta.id ta_id,
_ta.descricao ta_descricao
from 
Endereco _end
left join Interferencia _int on _int.endereco = _end.id
left join tipo_ato _ta on _ta.id = _int.tipo_ato
where _end.logradouro like '%%'

```

