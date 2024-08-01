# To Do List

## 17 de Junho de 2024
- [ ] **Buscar por interferência**
    - Decidir se a busca será feita por coordenadas convertendo-as para string ou criando um círculo no SQL e buscar coordenadas contidas neste círculo, para encontrar proximidade com o ponto solicitado.
 	
## 31 de Julho de 2024
- [ ] **Tabelas Acessórias da Interferência**
    - **Interferencia_Tipo**

| ID  | Descrição                        |
| --- | -------------------------------- |
| 1   | Superficial                      |
| 2   | Subterrânea                      |
| 3   | Lançamento de Águas Pluviais     |
| 4   | Canal                            |
| 5   | Caminhão Pipa                    |
| 6   | Lançamento de Efluentes          |
| 7   | Barragem                         |

    - **Tipo_Outorga**

| ID  | Descrição           |
| --- | ------------------- |
| 1   | Outorga             |
| 2   | Outorga Prévia      |
| 3   | Registro            |

    - **Subtipo_Outorga**

| ID  | Descrição           |
| --- | ------------------- |
| 1   | Renovação           |
| 2   | Modificação         |
| 3   | Transferência       |
| 4   | Suspensão/Revogação |
| 5   |                     |

    - **Situacao_Processo**

| ID  | Descrição                  |
| --- | -------------------------- |
| 1   | Arquivado                  |
| 2   | Em Análise                 |
| 3   | Outorgado                  |
| 4   | Vencida                    |
| 5   | Arquivado (CNRH 16)        |
| 6   | Pendência                  |
| 7   | Indeferido                 |
| 8   | Revogado                   |

    - **Tipo_Ato**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Despacho         |
| 2   | Portaria         |
| 3   | Registro         |
| 4   | Resolução        |
| 5   | Resolução ANA    |
| 6   | Portaria DNAEE   |

    - **Bacia Hidrográfica**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Bacia 1          |
| 2   | Bacia 2          |
| 3   | Bacia 3          |

    - **UNIDADE_HIDROGRAFICA**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Unidade 1        |
| 2   | Unidade 2        |
| 3   | Unidade 3        |

- [ ] **Tabelas Acessórias Subterrâneo**
    - **SubSistema**
    
| ID  | Descrição        |
| --- | ---------------- |
| 1   | SubSistema 1     |
| 2   | SubSistema 2     |

    - **Tipo Poço**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Manual           |
| 2   | Tubular          |

    - **Forma_Captacao**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Bombeamento      |
| 2   | Gravidade        |

    - **Local_Captacao**

| ID  | Descrição        |
| --- | ---------------- |
| 1   | Nascente         |
| 2   | Rio              |
| 3   | Reservatório     |
| 4   | Canal            |
| 5   | Lago Natural     |
