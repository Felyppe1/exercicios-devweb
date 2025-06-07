# Introdução ao Desenvolvimento Web

Repositório para fazer os exercícios da disciplina Desenvolvimento Dev Web

## 🛠 Tecnologias utilizadas

- Java 17+
- Servlets & JSP
- Apache Tomcat
- PostgreSQL
- Dev Containers

---

## 🚀 Como rodar o projeto

### Liberar permissão de arquivos

```bash
chmod +x build.sh
chmod +x /usr/local/sdkman/candidates/tomcat/current/bin/*.sh
```

### Definir versão atual do Java

```bash
sdk install java 17-open
```

### Definir variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto e preencha as variáveis que estão no `.env.example`

#### Subir o banco

```bash
docker compose up
```

#### Inicializar o banco com script `init.sql`

```bash
docker cp init.sql exercicios-devweb-db:/init.sql
docker exec -it exercicios-devweb-db bash
psql -U postgres -d devweb -f /init.sql
```

### Iniciar o tomcat

```bash
/usr/local/sdkman/candidates/tomcat/current/bin/catalina.sh start
```

#### Compilar o projeto (se necessário)

```bash
./build.sh
```

#### 4. Acessar a aplicação

Abra no navegador: http://localhost:8080/exercicios

https://legendary-dollop-r965w4w5vjxfwjvj-8080.app.github.dev/