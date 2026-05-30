ALTER TABLE users
    DROP CONSTRAINT fk_users_academy;

ALTER TABLE users
    ADD CONSTRAINT fk_users_academy
        FOREIGN KEY (academy_id) REFERENCES academies(id) ON DELETE CASCADE;