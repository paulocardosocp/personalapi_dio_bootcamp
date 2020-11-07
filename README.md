<h2>API REST de um sistema de gerenciamento de pessoas com Spring Boot</h2>
<h4>Projeto do bootcamp Java Developer, da Digital Innovation One com Rodrigo Peleias</h4>

O projeto desenvolvido utiliza:

* Mapeamento Objeto-Relacional (com JPA) 
* H2 Database Engine
* Hibernate Validator
* MapStruct
* Projeto Lombok
* Testes unitários

Para executar o projeto no terminal, navegue até o diretório do projeto e digite o comando abaixo:

```shell script
mvn spring-boot:run 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v2/person
```


São necessários os seguintes pré-requisitos para a execução do projeto:

* Java 11 ou versões superiores.
* Maven 3.6.3 ou versões superiores.


Foi utilizado o Postman para o consumo da API.

Exemplos de entrada

* Endpoint: http://localhost:8080/api/v2/person (POST)
```
{
	"firstName":"Kakashi",
	"lastName":"Hataki",
	"cpf":"095.046.470-81",
	"birthDate":"01-01-1984",
	"phones":
	[{
		"type":"COMMERCIAL",
		"number":"(98)3222-2122"
	}]
}
```

* Endpoint: http://localhost:8080/api/v2/department (POST)
```
{
    "description": "Aldeia da Folha"
}
```

* Endpoint: http://localhost:8080/api/v2/project (POST)
```
{
    "title":"Montagem da Prova Chunin",
    "department":
        {
            "id":1
        }
}
```

* Endpoint: http://localhost:8080/api/v2/person-project (POST)
```
[{
    "person": {
        "id": 1,
        "firstName":"Kakashi",
        "lastName":"Hataki",
        "cpf":"095.046.470-81",
        "birthDate":"01-01-1984",
        "phones":
        [{
            "type":"COMMERCIAL",
            "number":"(98)3222-2122"
        }]
    },
    "project": {
        "id": 1,
        "title": "Montagem da Prova Chunin",
        "department": {
            "id":1
        }
    },
    "manager": true
	
}]
```



