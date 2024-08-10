package com.library.api.model.dto;

import com.library.api.model.Borrowing;
import com.library.api.util.validation.CheckDateFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
public class UpdateBorrowingDto {
    private final Optional<@CheckDateFormat(pattern = "YYYY-MM-DD", message = "ReturnedOn date is invalid.") String>
            returnedOn = Optional.empty();

    public Borrowing migrate(Borrowing borrowing) {
        returnedOn.ifPresent(date -> borrowing.setReturnedOn(date));

        return borrowing;
    }
}
