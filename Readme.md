# Introdução ao Desenvolvimento Web

Repositório para fazer os exercícios da disciplina Desenvolvimento Dev Web

## Tecnologias utilizadas

- Java 17+
- Servlets & JSP
- Apache Tomcat
- PostgreSQL
- Dev Containers

## Como rodar o projeto (com Dev Container)

### 1. Configurar variáveis de ambiente:

Copie o arquivo de exemplo `.env.example` para o `.env` dentro da pasta `/.devcontainer` e preencha as variáveis necessárias:
```bash
cp .env.example ./.devcontainer/.env
# Edite o arquivo .env com os valores apropriados
```

### 2. Liberar permissão de arquivos

```bash
chmod +x /usr/local/sdkman/candidates/tomcat/current/bin/*.sh
```

### 3. Buildar e jogar no Tomcat

```bash
build.devcontainer.sh
```

### 4. Iniciar o tomcat

```bash
/usr/local/sdkman/candidates/tomcat/current/bin/catalina.sh start
```

### 5. Acessar a aplicação

Abra no navegador: http://localhost:8080

### Para criar tabelas no banco:

```bash
docker cp db.sql exercicios-devweb-db:/db.sql
docker exec -it exercicios-devweb-db bash
psql -U postgres -d devweb -f /db.sql
```