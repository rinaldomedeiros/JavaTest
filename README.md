# API SigaBem
## Projeto da API que calcula o valor total do frete e a data prevista da entrega.

## Tecnologias
As seguintes ferramentas foram usadas na construção do projeto:
- Java 11
- Springboot 2.5.4
- Maven
- PostgreSQL
- Flyway
- Swagger

## Configuração
É necessário ter o banco de dados Postgresql previamente instalado e configurado antes de rodar o projeto.
 Preencher os dados do banco de dados Postgresql no arquivo application.properties conforme abaixo:
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/ ${DATABASE_NAME}
spring.datasource.username= ${USER_NAME_DB}
spring.datasource.password= ${PASSWORD}
```


## Migrations
A tabela cotacao será criada automaticamente através das migrations do flayway


## Iniciar Aplicação
Através do CMD, abra a pasta do projeto e execute o comando abaixo:
```sh
mvn spring-boot:run
```

## Documentação:
http://localhost:8080/swagger-ui.html#/

## Endpoint
O endpoint principal é o de entrada, que é usado para calcular o frete e salvar os dados da cotação no banco de dados.
Os dados devem ser passados no formato JSON, conforme exemplo abaixo:
- POST/ cotacoes
```sh
{
  "peso":"10.0",
  "cepOrigem":"71881722",
  "cepDestino":"72319030",
  "nomeDestinatario":"Fulano de Tal"
}
```

### Também foram desenvolvidos os endpoints para manutenção dos dados das cotações e estão relacionados abaixo:
- GET/ cotacoes
```sh
Lista todas as cotações no formato JASON com o seguinte padrão:
{
  "id": 2,
  "peso": 5.0,
  "cepOrigem": "71881722",
  "cepDestino": "72319030",
  "nomeDestinatario": "Fulano de Tal",
  "vlTotalFrete": 10,
  "dataPrevistaEntrega": "2021-09-16",
  "dataConsulta": "2021-09-26"
}
```

- GET/ cotacoes/{id}
```sh
Consulta a cotação pelo id informado na requisição.
```

- PUT/ cotacoes/{id}
```sh
Atualiza a cotação com o id informado na requisição.
Os dados devem ser passados no formato JSON, conforme exemplo abaixo:
{
  "peso":"10.0",
  "cepOrigem":"71881722",
  "cepDestino":"72319030",
  "nomeDestinatario":"Fulano de Tal"
}
```


- DELETE/ cotacoes/{id}
```sh
Exclui a cotação com o id informado na requisição.
```,
