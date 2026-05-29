CREATE TABLE alunos (
    id SERIAL PRIMARY KEY NOT NULL,
    academy_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    sexo VARCHAR(10) NOT NULL,
    data_nascimento DATE NOT NULL,
    foto_url VARCHAR,
    observacoes TEXT,
    bairro VARCHAR(100),
    rua VARCHAR(100),
    numero VARCHAR(20),
    cidade VARCHAR(100),
    estado VARCHAR(100),
    cep VARCHAR(20),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_alunos_academy FOREIGN KEY (academy_id) REFERENCES academies(id)
);