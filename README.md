# 🎬 Movietex

Uma aplicação web para gerenciamento e consulta de filmes, desenvolvida com tecnologias robustas de backend e frontend.

## 📋 Sobre o Projeto

**Movietex** é uma plataforma web que permite aos usuários:
- ✅ Registrar-se e fazer login na aplicação
- ✅ Consultar um catálogo de filmes com informações detalhadas
- ✅ Gerenciar filmes (admin)
- ✅ Visualizar avaliações e detalhes de filmes
- ✅ Interface intuitiva e responsiva

O projeto combina um backend robusto em **Java** com um frontend moderno utilizando **JavaScript** e **Tailwind CSS**, com dados persistidos em um banco de dados **PostgreSQL**.

---

## 🛠️ Tecnologias Utilizadas

### **Backend (84.8%)**
- **Java 17** - Linguagem de programação principal
- **Maven** - Gerenciador de dependências e build
- **Servlets & JSP** - Framework web Java
- **PostgreSQL JDBC** - Conector para banco de dados
- **JSTL** - Taglibs para templates JSP
- **Gson** - Serialização/desserialização JSON
- **SLF4J + Logback** - Logging estruturado
- **JUnit 5** - Testes unitários
- **Testcontainers** - Testes integrados com containers Docker

### **Frontend (7.2%)**
- **JavaScript** - Lógica do lado do cliente
- **Tailwind CSS** - Framework CSS utilitário para estilização
- **HTML5** - Markup estruturado

### **Banco de Dados (8%)**
- **PostgreSQL** - Banco de dados relacional
- **PL/pgSQL** - Linguagem procedural do PostgreSQL (triggers, stored procedures)

---

## 📦 Dependências Principais

```xml
<!-- Servlet & JSP -->
javax.servlet:javax.servlet-api:4.0.1
javax.servlet:jstl:1.2
javax.servlet.jsp:javax.servlet.jsp-api:2.3.3

<!-- Database -->
org.postgresql:postgresql:42.7.3

<!-- JSON -->
com.google.code.gson:gson:2.8.9

<!-- Testing -->
org.junit.jupiter:junit-jupiter-api:5.9.2
org.junit.jupiter:junit-jupiter-engine:5.9.3
org.testcontainers:testcontainers:1.19.0
org.testcontainers:junit-jupiter:1.19.0
org.testcontainers:postgresql:1.19.0

<!-- Logging -->
org.slf4j:slf4j-api:2.0.13
ch.qos.logback:logback-classic:1.5.6
