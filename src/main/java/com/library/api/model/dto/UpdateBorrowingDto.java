package com.library.api.model.dto;

import com.library.api.model.Borrowing;
import com.library.api.util.validation.CheckDateFormat;
import lombok.Data;

import java.util.Optional;

@Data
public class UpdateBorrowingDto {
    private final Optional<@CheckDateFormat(pattern = "YYYY-MM-DD", message = "ReturnedOn date is invalid.") String>
            returnedOn = Optional.empty();

    public Borrowing migrate(Borrowing borrowing) {
        returnedOn.ifPresent(date -> borrowing.setReturnedOn(date));

        return borrowing;
    }
}
