package com.library.api.model.dto;

import com.library.api.model.Borrowing;
import com.library.api.util.validation.CheckDateFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CreateBorrowingDto {
    @NotNull(message = "Borrowing date is required.")
    @CheckDateFormat(pattern = "YYYY-MM-DD", message = "Borrowing date is invalid.")
    private String borrowingDate;

    @NotNull(message = "Borrowing date is required.")
    @CheckDateFormat(pattern = "YYYY-MM-DD", message = "Borrowing date is invalid.")
    private String returnDate;

    public Borrowing toBorrowing() {
        return new Borrowing()
                .setBorrowingDate(borrowingDate)
                .setReturnDate(returnDate);
    }
}
