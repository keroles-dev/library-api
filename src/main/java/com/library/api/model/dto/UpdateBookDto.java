package com.library.api.model.dto;

import com.library.api.model.Book;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import java.util.Optional;

@Data
@Builder
public class UpdateBookDto {
    private final Optional<@ISBN(message = "ISBN number is invalid.") String> isbn = Optional.empty();

    private final Optional<
            @Size(min = 1, max = 255, message = "Title length must be between 1 and 255 character.")
            @NotBlank(message = "Title length must be between 1 and 255 character.") String> title = Optional.empty();

    private final Optional<
            @Size(min = 1, max = 255, message = "Author length must be between 1 and 255 character.")
            @NotBlank(message = "Author length must be between 1 and 255 character.") String> author = Optional.empty();

    private final Optional<
            @Digits(integer = 4, fraction = 0, message = "Publication year length must be between 1 and 4 digit.")
                    Integer> publicationYear = Optional.empty();

    public Book migrate(Book book) {
        isbn.ifPresent(s -> book.setIsbn(s));

        title.ifPresent(s -> book.setTitle(s));

        author.ifPresent(s -> book.setAuthor(s));

        publicationYear.ifPresent(integer -> book.setPublicationYear(integer));

        return book;
    }
}
