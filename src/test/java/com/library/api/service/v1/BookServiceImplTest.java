package com.library.api.service.v1;

import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.dto.CreateBookDto;
import com.library.api.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    private static final String TEST_ISBN = "978-1-56619-909-4";
    private static final String TEST_TITLE = "book title";
    private static final String TEST_AUTHOR = "book author";
    private static final int TEST_PUBLICATION_YEAR = 1990;
    private CreateBookDto createBookDto;

    @BeforeEach
    void setUp() {
        createBookDto = CreateBookDto.builder()
                .isbn(TEST_ISBN).title(TEST_TITLE).author(TEST_AUTHOR).publicationYear(TEST_PUBLICATION_YEAR).build();
    }

    @Test
    void createBookWithAlreadyExistsIsbnFail() throws ResourceAlreadyExistsException {
        when(bookRepository.findByIsbn(createBookDto.getIsbn())).thenReturn(List.of(new Book()));
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            bookService.create(createBookDto);
        });
    }

    @Test
    void createBookSuccess() throws ResourceAlreadyExistsException {
        when(bookRepository.findByIsbn(createBookDto.getIsbn())).thenReturn(List.of());
        when(bookRepository.save(createBookDto.toBook())).thenReturn(createBookDto.toBook());

        Book book = bookService.create(createBookDto);

        verify(bookRepository, times(1)).findByIsbn(createBookDto.getIsbn());
        verify(bookRepository, times(1)).save(any(Book.class));

        assertEquals(book, createBookDto.toBook());
    }
}