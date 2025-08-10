# Literalura
Sistema de catálogo de livros, com interação via console. Desafio proposto no bootcamp Alura ONE.
## Funcionalidades
- Buscar livro pelo título (chamada a API) 

- Listar autores registrados 

- Listar livros registrados 

- Listar autores vivos em um determinado ano 

- Listar livros em um determinado idioma
## Variáveis de Ambiente
Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no arquivo src/main/resources/application.properties. 

`spring.application.name=literalura`

`spring.datasource.url=`

`spring.datasource.username=`

`spring.datasource.password=`
 
`spring.datasource.driver-class-nome=org.postgresql.Driver`

`spring.jpa.hibernate.ddl-auto=update`
 
`spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect` 

`spring.jpa.show-sql=true` `spring.jpa.format-sql=true`


## Referência
 - [API - Gutendex](https://gutendex.com/)
