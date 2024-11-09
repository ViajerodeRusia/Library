-- liquibase formatted sql

-- changeset dsiliukov:${changeset.id.sequence}

-- Добавление данных авторов
 INSERT INTO author (first_name, last_name, birth_date) VALUES ('John', 'Doe', '1980-01-01');
 INSERT INTO author (first_name, last_name, birth_date) VALUES ('Alex', 'Shelby', '1985-02-02');
 INSERT INTO author (first_name, last_name, birth_date) VALUES ('Mark', 'Twain', '1835-11-30');

 -- Добавление данных книг
 INSERT INTO book (title, year) VALUES ('Book 1', 2021);
 INSERT INTO book (title, year) VALUES ('Book 2', 2020);
 INSERT INTO book (title, year) VALUES ('Book 3', 2023);

 -- Добавление данных читателей
 INSERT INTO reader (phone_number, first_name, last_name, gender, birth_date) VALUES ('1234567890', 'Alice', 'Smith', 'Female', '1990-05-15');
 INSERT INTO reader (phone_number, first_name, last_name, gender, birth_date) VALUES ('0987654321', 'Bob', 'Johnson', 'Male', '1988-03-22');
 INSERT INTO reader (phone_number, first_name, last_name, gender, birth_date) VALUES ('5555555555', 'Charlie', 'Brown', 'Male', '1995-07-30');

 -- Связывание авторов и книг
 INSERT INTO author_book (author_id, book_id) VALUES (1, 1);  -- John Doe написал Book 1
 INSERT INTO author_book (author_id, book_id) VALUES (1, 2);  -- John Doe написал Book 2
 INSERT INTO author_book (author_id, book_id) VALUES (2, 1);  -- Alex Shelby также написала Book 1
 INSERT INTO author_book (author_id, book_id) VALUES (3, 3);  -- Mark Twain написал Book 3