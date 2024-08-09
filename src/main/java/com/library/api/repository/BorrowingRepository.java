package com.library.api.repository;

import com.library.api.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    @Query(value = "SELECT * FROM borrowings WHERE book_id = ?1 AND patron_id = ?2 AND returned_on IS NULL", nativeQuery = true)
    List<Borrowing> getActiveBorrowingByBookIdAndPatronId(long bookId, long patronId);
}