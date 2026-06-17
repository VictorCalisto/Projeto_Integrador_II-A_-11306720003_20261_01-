-- Criação do banco de dados (opcional, pode ser criado via docker-compose)
-- CREATE DATABASE agenda;

-- Criação da tabela de contatos
CREATE TABLE IF NOT EXISTS contatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índice no nome para buscas mais rápidas
CREATE INDEX IF NOT EXISTS idx_contato_nome ON contatos(nome);
