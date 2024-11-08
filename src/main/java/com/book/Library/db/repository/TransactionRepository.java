package com.book.Library.db.repository;

import com.book.Library.db.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT a, COUNT(t) as count " +
            "FROM Transaction t " +
            "JOIN t.book b " +
            "JOIN b.authors a " +
            "GROUP BY a " +
            "ORDER BY count DESC")
    List<Object[]> findMostPopularAuthor(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT r, COUNT(t) as count " +
            "FROM Transaction t " +
            "JOIN t.reader r " +
            "GROUP BY r " +
            "ORDER BY count DESC")
    List<Object[]> findMostFrequentReader();

    @Query("SELECT r, COUNT(t) as unreturnedCount " +
            "FROM Transaction t " +
            "JOIN t.reader r " +
            "WHERE t.operationType = 'borrow' " +
            "AND NOT EXISTS (SELECT 1 FROM Transaction t2 " +
            "                WHERE t2.reader.phoneNumber = r.phoneNumber " +
            "                AND t2.book.id = t.book.id " +
            "                AND t2.operationType = 'return' " +
            "                AND t2.operationTime > t.operationTime) " +
            "GROUP BY r " +
            "ORDER BY unreturnedCount DESC")
    List<Object[]> findReadersSortedByUnreturnedBooks();
}
