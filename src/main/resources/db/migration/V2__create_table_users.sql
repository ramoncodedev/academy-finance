CREATE TABLE users (
    id       SERIAL PRIMARY KEY NOT NULL,
    academy_id INTEGER NOT NULL,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL,
    ativo    BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_users_academy FOREIGN KEY (academy_id) REFERENCES academies(id)
);
