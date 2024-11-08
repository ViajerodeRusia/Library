package com.book.Library.service;

import com.book.Library.controller.dto.AuthorDTO;
import com.book.Library.controller.dto.ReaderDTO;
import com.book.Library.controller.dto.TransactionDTO;
import com.book.Library.db.entity.Author;
import com.book.Library.db.entity.Book;
import com.book.Library.db.entity.Reader;
import com.book.Library.db.entity.Transaction;
import com.book.Library.db.repository.AuthorRepository;
import com.book.Library.db.repository.BookRepository;
import com.book.Library.db.repository.ReaderRepository;
import com.book.Library.db.repository.TransactionRepository;
import com.book.Library.exception.ResultsNotFound;
import com.book.Library.mapper.AuthorMapper;
import com.book.Library.mapper.ReaderMapper;
import com.book.Library.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AuthorMapper authorMapper;
    private final ReaderMapper readerMapper;



    @Override
    public TransactionDTO performTransaction(Long bookId, String readerPhone, String operationType) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        try {
            if (book.getIsBorrowed() && operationType.equals("borrow")) {
                throw new ResultsNotFound("Book is already borrowed");
            }
        } catch (ResultsNotFound e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        Reader reader = readerRepository.findById(readerPhone)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setReader(reader);
        transaction.setOperationType(operationType);
        transaction.setOperationTime(LocalDateTime.now());
        changeBookStatus(operationType, book);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

    private static void changeBookStatus(String operationType, Book book) {
        switch (operationType) {
            case "borrow":
                book.setIsBorrowed(true);
                break;
            default: book.setIsBorrowed(false);
        }
    }


    @Override
    public AuthorDTO findMostPopularAuthor(String startDate, String endDate) {
        try {
            LocalDateTime start = LocalDateTime.parse(startDate);
            LocalDateTime end = LocalDateTime.parse(endDate);
            List<Object[]> results = transactionRepository.findMostPopularAuthor(start, end);
            try {
                if (results.isEmpty()) {
                    throw new ResultsNotFound("Results not found");
                }
            } catch (ResultsNotFound e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            Object[] mostPopular = results.get(0);
            Author author = (Author) mostPopular[0];
            return authorMapper.toDto(author);
        } catch (DateTimeParseException e) {
            // Если формат даты неверный
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ReaderDTO findMostFrequentReader() {
        List<Object[]> results = transactionRepository.findMostFrequentReader();
        try {
            if (results.isEmpty()) {
                throw new ResultsNotFound("Results not found");
            }
        } catch (ResultsNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        Object[] mostFrequent = results.get(0);
        Reader reader = (Reader) mostFrequent[0];
        return readerMapper.toDto(reader);
    }

    @Override
    public List<ReaderDTO> getReadersSortedByUnreturnedBooks() {
        List<Object[]> results = transactionRepository.findReadersSortedByUnreturnedBooks();
        try {
            if (results.isEmpty()) {
                throw new ResultsNotFound("Results not found");
            }
        } catch (ResultsNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        List<ReaderDTO> readerDTOs = new ArrayList<>();
        for (Object[] result : results) {
            Reader reader = (Reader) result[0];
            Long unreturnedCount = (Long) result[1];
            ReaderDTO readerDTO = readerMapper.toDto(reader);
            readerDTO.setUnreturnedBooksCount(unreturnedCount);
            readerDTOs.add(readerDTO);
        }
        return readerDTOs;
    }
}
