# 💸 DevBank

Repositório para fazer os exercícios da disciplina Desenvolvimento Dev Web

## 🛠 Tecnologias utilizadas

- Java 17+
- Servlets & JSP
- Apache Tomcat
- PostgreSQL
- Docker

---

## 🚀 Como rodar o projeto

### ✅ Usando Docker (recomendado)

> Certifique-se de ter o Docker instalado antes de prosseguir.
> 

#### 1. Subir os containers:

```bash
docker compose up --build
```

#### 2. Inicializar o banco com script `init.sql`:

```bash
docker cp init.sql exercicios-devweb-db:/init.sql
docker exec -it exercicios-devweb-db bash
psql -U postgres -d devweb -f /init.sql
```

#### 3. Compilar o projeto (se necessário):

```bash
./build.sh
```

#### 4. Acessar a aplicação:

Abra no navegador: http://localhost:8080/exercicios/home

---

## 👨‍💻 Desenvolvedores

- Luiz Felyppe Nunes dos Santos
- Mayara Frazão Guaraciaba de Lima
- Thiago Pereira Araujo
- Rodrigo Dias
- Gabriel