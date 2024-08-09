package com.library.api.model.dto;

import com.library.api.model.Patron;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class UpdatePatronDto {
    private final Optional<
            @Size(min = 1, max = 255, message = "Name length must be between 1 and 255 character.")
            @NotBlank(message = "Name length must be between 1 and 255 character.") String> name = Optional.empty();

    private final Optional<
            @Size(min = 1, max = 500, message = "Contact info length must be between 1 and 500 character.")
            @NotBlank(message = "Contact info length must be between 1 and 255 character.") String> contactInfo = Optional.empty();

    public Patron migrate(Patron patron) {
        name.ifPresent(s -> patron.setName(s));

        contactInfo.ifPresent(s -> patron.setContactInfo(s));

        return patron;
    }
}
