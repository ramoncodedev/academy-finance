ALTER TABLE alunos
    DROP CONSTRAINT fk_alunos_academy;

ALTER TABLE alunos
    ADD CONSTRAINT fk_alunos_academy
        FOREIGN KEY (academy_id) REFERENCES academies(id) ON DELETE CASCADE;