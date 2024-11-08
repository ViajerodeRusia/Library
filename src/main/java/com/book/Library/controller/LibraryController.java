package com.book.Library.controller;

import com.book.Library.controller.dto.AuthorDTO;
import com.book.Library.controller.dto.ReaderDTO;
import com.book.Library.controller.dto.TransactionDTO;
import com.book.Library.db.entity.Author;
import com.book.Library.db.entity.Reader;
import com.book.Library.db.entity.Transaction;
import com.book.Library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDTO> performTransaction(
            @RequestParam Long bookId,
            @RequestParam String readerPhone,
            @RequestParam String operationType) {

        TransactionDTO transactionDTO = libraryService.performTransaction(bookId, readerPhone, operationType);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/popular-author")
    public ResponseEntity<AuthorDTO> getMostPopularAuthor(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        AuthorDTO authorDTO = libraryService.findMostPopularAuthor(startDate, endDate);
        return new ResponseEntity<>(authorDTO, HttpStatus.OK);
    }

    @GetMapping("/most-frequent-reader")
    public ResponseEntity<ReaderDTO> getMostFrequentReader() {
        ReaderDTO readerDTO = libraryService.findMostFrequentReader();
        return new ResponseEntity<>(readerDTO, HttpStatus.OK);
    }

    @GetMapping("/readers/unreturned")
    public ResponseEntity<List<ReaderDTO>> getReadersSortedByUnreturnedBooks() {
        List<ReaderDTO> readerDTOS = libraryService.getReadersSortedByUnreturnedBooks();
        return new ResponseEntity<>(readerDTOS, HttpStatus.OK);
    }
}
