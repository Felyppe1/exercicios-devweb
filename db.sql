DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
    PRIMARY KEY (id),
    id VARCHAR(255) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)