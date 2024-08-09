package com.library.api.service.v1.interfaces;

import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Patron;
import com.library.api.model.dto.CreatePatronDto;
import com.library.api.model.dto.UpdatePatronDto;

import java.util.List;

public interface PatronService {
    Patron create(CreatePatronDto createPatronDto);

    Patron get(long id) throws ResourceNotFoundException;

    List<Patron> getAll();

    Patron update(long id, UpdatePatronDto updatePatronDto) throws ResourceNotFoundException;

    Patron delete(long id) throws ResourceNotFoundException;
}