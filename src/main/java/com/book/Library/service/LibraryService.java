package com.book.Library.service;

import com.book.Library.controller.dto.AuthorDTO;
import com.book.Library.controller.dto.ReaderDTO;
import com.book.Library.controller.dto.TransactionDTO;
import com.book.Library.db.entity.Author;
import com.book.Library.db.entity.Reader;
import com.book.Library.db.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;
public interface LibraryService {

    TransactionDTO performTransaction(Long bookId, String readerPhone, String operationType);

    AuthorDTO findMostPopularAuthor(String parse, String parse1);

    ReaderDTO findMostFrequentReader();

    List<ReaderDTO> getReadersSortedByUnreturnedBooks();
}
