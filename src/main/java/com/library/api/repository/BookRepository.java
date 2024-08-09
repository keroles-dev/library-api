package com.library.api.repository;

import com.library.api.model.Book;
import com.library.api.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books WHERE isbn = ?1", nativeQuery = true)
    List<Book> findByIsbn(String isbn);
}