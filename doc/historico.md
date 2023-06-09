
###  Pesquisas
 
[how to change location - h2](https://groups.google.com/g/play-framework/c/qqV1soIfH48)

[SpringBoot - 2022 - Michelli Brito](https://www.youtube.com/watch?v=LXRU-Z36GEU)

21/03/2023
Testando o chatGPT pesquisei como fazer um relacionamento OneToMany em springboot. 
Ex: create a java springboot one-to-many relationship between two tables, table type has multiples documents

Resposta:

```
@Entity
@Table(name = "document_types")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

    // constructor, getters, setters
}

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    // constructor, getters, setters
}

```

14/04/2023

	Cadastro de processo iniciado, fazendo relacionamento com o próprio processo. Assim podemos criar um processo principal e outros processos relacionados com este pricipal, como se fosse um processo pai com vários filhos.

Solução: 
[stackoverflow - json - infinite recursion](https://stackoverflow.com/questions/47693110/could-not-write-json-infinite-recursion-stackoverflowerror-nested-exception)

Problemas: 

	1. Quando estou pedindo a lista de tipos de documentos, está automaticamente listando os documentos do tipo 1 - requerimento. Eu quero apenas a lista de tipos, sem que se traga todos os  documentos que são requerimentos ou outro tipo.
		Resolvi com @JsonIgnore 
	2. Não está trazendo na tabela documentos, o tipo do documento doc_tipo.
		Resolvido com mudanças na forma de criar os relacionamentos:

``` 
			// Documento
			@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "td_id", scope = TipoDocumentoModel.class)
			@ManyToOne
			@JoinColumn(name = "doc_tipo")
			private TipoDocumentoModel doc_tipo;
			
			// Tipo_Documento
			@JsonIgnore
			@OneToMany(mappedBy = "doc_tipo")
			private List<DocumentoModel> documentos = new ArrayList<DocumentoModel>();
			
```
	
