# To Do List

## 17 de Junho de 2024
- [ ] **Buscar por interfer�ncia**
    - Decidir se a busca ser� feita por coordenadas convertendo-as para string ou criando um c�rculo no SQL e buscar coordenadas contidas neste c�rculo, para encontrar proximidade com o ponto solicitado.
 	
## 31 de Julho de 2024
- [ ] **Tabelas Acess�rias da Interfer�ncia**
    - **Interferencia_Tipo**

| ID  | Descri��o                        |
| --- | -------------------------------- |
| 1   | Superficial                      |
| 2   | Subterr�nea                      |
| 3   | Lan�amento de �guas Pluviais     |
| 4   | Canal                            |
| 5   | Caminh�o Pipa                    |
| 6   | Lan�amento de Efluentes          |
| 7   | Barragem                         |

    - **Tipo_Outorga**

| ID  | Descri��o           |
| --- | ------------------- |
| 1   | Outorga             |
| 2   | Outorga Pr�via      |
| 3   | Registro            |

    - **Subtipo_Outorga**

| ID  | Descri��o           |
| --- | ------------------- |
| 1   | Renova��o           |
| 2   | Modifica��o         |
| 3   | Transfer�ncia       |
| 4   | Suspens�o/Revoga��o |
| 5   |                     |

    - **Situacao_Processo**

| ID  | Descri��o                  |
| --- | -------------------------- |
| 1   | Arquivado                  |
| 2   | Em An�lise                 |
| 3   | Outorgado                  |
| 4   | Vencida                    |
| 5   | Arquivado (CNRH 16)        |
| 6   | Pend�ncia                  |
| 7   | Indeferido                 |
| 8   | Revogado                   |

    - **Tipo_Ato**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Despacho         |
| 2   | Portaria         |
| 3   | Registro         |
| 4   | Resolu��o        |
| 5   | Resolu��o ANA    |
| 6   | Portaria DNAEE   |

    - **Bacia Hidrogr�fica**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Bacia 1          |
| 2   | Bacia 2          |
| 3   | Bacia 3          |

    - **UNIDADE_HIDROGRAFICA**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Unidade 1        |
| 2   | Unidade 2        |
| 3   | Unidade 3        |

- [ ] **Tabelas Acess�rias Subterr�neo**
    - **SubSistema**
    
| ID  | Descri��o        |
| --- | ---------------- |
| 1   | SubSistema 1     |
| 2   | SubSistema 2     |

    - **Tipo Po�o**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Manual           |
| 2   | Tubular          |

    - **Forma_Captacao**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Bombeamento      |
| 2   | Gravidade        |

    - **Local_Captacao**

| ID  | Descri��o        |
| --- | ---------------- |
| 1   | Nascente         |
| 2   | Rio              |
| 3   | Reservat�rio     |
| 4   | Canal            |
| 5   | Lago Natural     |


# 25-09-2024

Verificar a edi��o do Estado ao editar um endere�o.


# 15/10/2024
	Remover o arquivo application.properties do .env para que seja poss�vel mudar update e create banco de dados.
	
	
# 17/10/2024
	Para deploy heroku
		1 - Mudar application.propeties para supabase
		2 - remover sql -> spring.jpa.show-sql=false
		3 - Comentar DatabaseConfig -> //@PropertySource("classpath:.env")
		
		
# 27/01/2025
- [] Não está deletando uma interferência com as finalidades e demandas. O cascade type all não está funcionando.

# 


# 31/01/2025
- [X Não está criando a coluna shape na compilação para o replit.
	Criei a coluna com sql: ALTER TABLE hidrogeo_poroso ADD COLUMN shape GEOMETRY(Geometry, 4674);

#06/02/2025
- [X] Verificar busca por documento. Busca por número do documento, endereço se possível etc. Não está buscando
	pelo número do documento, por exemplo: 140918752
		Agora busca por logradouro, número do processo, número do documento e número sei do documento.

#11/02/2025
- [] Deletar interferência
	Não está funcionando, não deleta, apesar de sumir no frontend.
	
#12/02/2025
- [X] Melhora pesquisa de anexo, adiconar processos com id e usuários com id.
#28/04/2025
* ** [] Buscar usuário por nome e cpf
	No momento só está buscando pelo nome.
	
#14/05/2025
- [X] Persistência Documento com Endereço
	Ao salvar o documento, a resposta não estava trazendo o bairro do endereço. 
	Resolvido em 14/05/2025.
	

	