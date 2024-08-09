package com.library.api.service.v1.interfaces;

import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.dto.CreateBookDto;
import com.library.api.model.dto.UpdateBookDto;

import java.util.List;

public interface BookService {
    Book create(CreateBookDto createBookDto) throws ResourceNotFoundException;

    Book get(long id) throws ResourceNotFoundException;

    List<Book> getAll();

    Book update(long id, UpdateBookDto updateBookDto) throws ResourceNotFoundException;

    Book delete(long id) throws ResourceNotFoundException;
}