# JS Drainage 
Utilizando repli.it junto com springboot e um banco de dados H2.


###  Pesquisas
  [how to change location - h2](https://groups.google.com/g/play-framework/c/qqV1soIfH48)

[SpringBoot - 2022 - Michelli Brito](https://www.youtube.com/watch?v=LXRU-Z36GEU)

21/03/2023
Testando do chatGPT. Pesquisei como fazer um relacionamento OneToMany em springboot. 
Ex: create a java springboot one-to-many relationship between two tables, table type has multiples documents

Resposta ChatGPT:
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





