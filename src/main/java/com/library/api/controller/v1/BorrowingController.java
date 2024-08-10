package com.library.api.controller.v1;

import com.library.api.exception.ResourceAlreadyExistsException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.model.Borrowing;
import com.library.api.model.dto.CreateBorrowingDto;
import com.library.api.model.dto.UpdateBorrowingDto;
import com.library.api.service.v1.interfaces.BorrowingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Validated
@RestController
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/v1/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> createBorrowing(
            @Positive(message = "The book id is invalid") @PathVariable long bookId,
            @Positive(message = "The patron id is invalid") @PathVariable long patronId,
            @Valid @RequestBody CreateBorrowingDto createBorrowingDto
    ) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Borrowing createdBorrowing = borrowingService.create(bookId, patronId, createBorrowingDto);

        return new ResponseEntity<>(createdBorrowing, HttpStatus.CREATED);
    }

    @PutMapping("/v1/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> updateBorrowing(
            @Positive(message = "The book id is invalid") @PathVariable long bookId,
            @Positive(message = "The patron id is invalid") @PathVariable long patronId,
            @Valid @RequestBody UpdateBorrowingDto updateBorrowingDto
    ) throws Exception {
        Borrowing updatedBorrowing = borrowingService.update(bookId, patronId, updateBorrowingDto);

        return new ResponseEntity<>(updatedBorrowing, HttpStatus.OK);
    }
}
