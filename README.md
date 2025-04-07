# Projeto Spring JDBC com Flyway

Este projeto tem como objetivo demonstrar o uso do **Spring Framework** com **JDBC puro**, utilizando **Flyway** para controle de versionamento de banco de dados, e aplicando conceitos fundamentais de modelagem de dados com diferentes tipos de **relacionamentos entre entidades**.

## 🧩 Tecnologias Utilizadas

- Java 17
- Spring Framework
- JDBC (Java Database Connectivity)
- MySQL
- Flyway (controle de migrações de banco de dados)
- Faker (geração de dados fake para testes)
- Gradle (gerenciador de dependências)

---

## 🧪 Objetivo do Projeto

O projeto visa ensinar na prática como:

- Configurar uma aplicação Spring sem JPA, utilizando **JDBC diretamente**.
- Realizar operações básicas de **CRUD** (Create, Read, Update, Delete).
- Versionar o banco de dados com o **Flyway**, garantindo consistência e rastreabilidade.
- Aplicar e manipular **relacionamentos entre entidades**:

### 🔄 Relacionamentos Abordados

- **1 para 1 (One-to-One):** Ex: cada funcionário pode ter um único endereço.
- **1 para N (One-to-Many):** Ex: um funcionário pode ter vários contatos.
- **N para N (Many-to-Many):** Ex: um funcionário pode ter acesso a vários módulos, e um módulo pode ser acessado por vários funcionários (através da tabela `accesses`).
- **N para N composto:** Similar ao N:N, mas com chaves compostas que também armazenam metadados ou restrições adicionais.

---

## 🛠 Flyway: o que é e para que serve?

O **Flyway** é uma ferramenta de **versionamento e migração de banco de dados**. Ele permite que você mantenha o histórico de alterações no schema de forma automatizada e reprodutível, evitando inconsistências entre ambientes (dev, staging, produção).

### ✅ Benefícios:
- Versionamento automatizado com scripts SQL numerados (`V202504021511__create_tables.sql`...). (2025-04-02 15:11)
- Integração simples com Spring Boot.
- Aplicação das alterações de forma ordenada e segura.

---

## 🔄 Alternativa ao Flyway

Uma alternativa bastante utilizada ao Flyway é o **Liquibase**. Ele também permite controlar versões do banco, mas com maior flexibilidade na escrita dos scripts (suporte a XML, YAML, JSON e SQL). É ideal para times que preferem uma abordagem mais descritiva ou declarativa.

---

## 📂 Estrutura do Projeto

