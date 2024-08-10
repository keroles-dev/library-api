
package com.library.api.service.v1.interfaces;

import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Borrowing;
import com.library.api.model.dto.CreateBorrowingDto;
import com.library.api.model.dto.UpdateBorrowingDto;

public interface BorrowingService {
    Borrowing create(long bookId, long patronId, CreateBorrowingDto createBorrowingDto) throws ResourceNotFoundException, ResourceAlreadyExistsException;

    Borrowing update(long bookId, long patronId, UpdateBorrowingDto updateBorrowingDto) throws Exception;
}