package com.library.api.service.v1;

import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.dto.CreateBookDto;
import com.library.api.model.dto.UpdateBookDto;
import com.library.api.repository.BookRepository;
import com.library.api.service.v1.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(CreateBookDto createBookDto) throws ResourceAlreadyExistsException {
        final List<Book> books = bookRepository.findByIsbn(createBookDto.getIsbn());

        if (!books.isEmpty()) throw new ResourceAlreadyExistsException("Book ISBN is already exists");

        return bookRepository.save(createBookDto.toBook());
    }

    @Override
    public Book get(long id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(long id, UpdateBookDto updateBookDto) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if(updateBookDto.getIsbn().isPresent()){
            final List<Book> books = bookRepository.findByIsbn(updateBookDto.getIsbn().get());

            if (!books.isEmpty()) throw new ResourceAlreadyExistsException("Book ISBN is already exists");
        }

        existingBook = updateBookDto.migrate(existingBook);

        bookRepository.save(existingBook);
        return existingBook;
    }

    @Override
    public Book delete(long id) throws ResourceNotFoundException {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.deleteById(id);
        return existingBook;
    }
}
