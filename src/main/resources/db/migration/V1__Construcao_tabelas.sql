
CREATE TABLE tb_revendas (
    id BIGSERIAL PRIMARY KEY,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    nome_social VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE tb_usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    perfil VARCHAR(20) NOT NULL
);

CREATE TABLE tb_oportunidades (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(255),
    cliente_nome VARCHAR(255) NOT NULL,
    cliente_email VARCHAR(255) NOT NULL,
    cliente_telefone VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    versao VARCHAR(255) NOT NULL,
    ano_modelo INTEGER NOT NULL,
    motivo_conclusao VARCHAR(255),
    revenda_id BIGINT NOT NULL,
    CONSTRAINT fk_revenda FOREIGN KEY (revenda_id) REFERENCES tb_revendas(id)
);
