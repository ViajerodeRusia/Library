-- liquibase formatted sql

-- changeset dsiliukov:${changeset.id.sequence}

-- Создание таблицы авторов
CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE
);

-- Создание таблицы книг
CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    is_borrowed BOOLEAN DEFAULT FALSE
);

-- Создание таблицы читателей
CREATE TABLE reader (
    phone_number VARCHAR(15) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(10),
    birth_date DATE
);

-- Создание таблицы транзакций
CREATE TABLE transaction (
    id BIGSERIAL PRIMARY KEY,
    operation_type VARCHAR(10) NOT NULL,
    operation_time TIMESTAMP NOT NULL,
    reader_phone VARCHAR(15) REFERENCES reader(phone_number),
    book_id BIGINT REFERENCES book(id)
);

-- Создание промежуточной таблицы для связи ManyToMany между авторами и книгами
CREATE TABLE author_book (
    author_id BIGINT REFERENCES author(id),
    book_id BIGINT REFERENCES book(id),
    PRIMARY KEY (author_id, book_id)
);
