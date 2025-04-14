# Introdução

Teste técnico para a vaga de desenvolvedor back-end BACKEND DEVELOPER INTERVIEW - 202502 - Mobiauto. Desenvolvimento com objetivo de demostrar a produtividade de construir APIs para agendamento de atendimento de oportunidades de revenda de veículos utilizando os frameworks Spring Boot e Spring Data em conjunto.

# Visão geral

O projeto é uma aplicação back-end com objetivo de demonstrar a produtividade de construir APIs utilizando os frameworks [Spring Boot](https://projects.spring.io/spring-boot) e [Spring Data](http://projects.spring.io/spring-data) em conjunto.

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é uma ferramenta que simplifica a configuração e execução de aplicações Java stand-alone,  com conceitos de dependências “starters”, auto configuração e servlet container embutidos é proporcionado uma grande produtividade desde o start-up da aplicação até sua ida a produção.

- [Spring Data](http://projects.spring.io/spring-data/) é um framework que abstrai o acesso ao modelo de dados, independente a tecnologia de base de dados.

- [Spring Security](https://spring.io/projects/spring-security) é um framework que fornece autenticação e controle de acesso a aplicações Java.

- [Flyway](https://documentation.red-gate.com/fd/why-database-migrations-184127574.html) é uma ferramenta de versionamento de banco de dados, que permite controlar as alterações no esquema do banco de dados e aplicar essas alterações de forma automatizada.

- [PostgreSQL](https://www.postgresql.org/) é um sistema de gerenciamento de banco de dados relacional e objeto-relacional, que é utilizado como banco de dados principal da aplicação.

- [JWT](https://jwt.io/) é um padrão aberto (RFC 7519) que define um formato compacto e auto-contido para a transmissão segura de informações entre partes como um objeto JSON. Esse padrão é utilizado para autenticação e autorização na aplicação.

# Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 21
PostgreSQL 13
Maven 3.8.5
Docker 27.5.1
Docker Compose 2.32.4
```

## Preparando ambiente

É necessário a criação da base de dados relacional no Postgres

```
CREATE DATABASE "mobiauto";
```
## Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
https://github.com/willtet/mobiauto-backend-202502
```
Feito isso, acesse o projeto:
```
cd mobiauto-backend-202502
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```

# Setup da aplicação com docker

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:

```
Docker 27.5.1
Docker Compose 2.32.4
```

## Preparando ambiente

Criar e executar container com docker compose
```
 docker compose up -d
```

Pronto. A aplicação está disponível em http://localhost:8080

# Suporte e Contato:

Em caso de dúvidas, entre em contato através dos canais:

- [LinkedIn](https://www.linkedin.com/in/willian-takashi/) Willian Takashi
- [E-mail](williantet@gmail.com) williantet@gmail.com
- [WhatsApp](https://wa.me/+5511984916483) (11) 98491-6483

# Observações importantes:

- Não foi possível desenvolver todos os requisitos do teste. O fluxo de teste mostra como realizaria testes unitários e de integração, mas não consegui finalizar a implementação.