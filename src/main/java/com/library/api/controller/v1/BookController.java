package com.library.api.controller.v1;

import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.dto.CreateBookDto;
import com.library.api.model.dto.UpdateBookDto;
import com.library.api.service.v1.interfaces.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto createBookDto) throws ResourceNotFoundException {
        Book createdBook = bookService.create(createBookDto);

        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(
            @Positive(message = "The book id is invalid") @PathVariable long id
    ) throws ResourceNotFoundException {
        Book book = bookService.get(id);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("")
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @Positive(message = "The book id is invalid") @PathVariable long id,
            @RequestBody UpdateBookDto updateBookDto
    ) throws ResourceNotFoundException {
        Book updatedBook = bookService.update(id, updateBookDto);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(
            @Positive(message = "The book id is invalid") @PathVariable long id
    ) throws ResourceNotFoundException {
        Book deletedBook = bookService.delete(id);

        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }
}
