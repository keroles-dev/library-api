package com.library.api.model.dto;

import com.library.api.model.Book;
import com.library.api.model.Patron;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreatePatronDto {
    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name can not be empty.")
    @Size(min = 1, max = 255, message = "Name length must be between 1 and 255 character.")
    @NotBlank(message = "Contact info can not be empty.")
    private String name;

    @NotNull(message = "Contact info is required.")
    @NotEmpty(message = "Contact info can not be empty.")
    @NotBlank(message = "Contact info can not be empty.")
    @Size(min = 1, max = 500, message = "Name length must be between 1 and 500 character.")
    private String contactInfo;

    public Patron toPatron() {
        return new Patron()
                .setName(name)
                .setContactInfo(contactInfo);
    }
}
