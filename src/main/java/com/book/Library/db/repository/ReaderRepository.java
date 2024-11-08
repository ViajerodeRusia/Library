package com.book.Library.db.repository;

import com.book.Library.db.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
