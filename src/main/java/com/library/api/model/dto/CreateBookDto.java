package com.library.api.model.dto;

import com.library.api.model.Book;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookDto {
    @NotNull(message = "ISBN number is required.")
    @ISBN(message = "ISBN number is invalid.")
    private String isbn;

    @NotNull(message = "Title is required.")
    @NotEmpty(message = "Title can not be empty.")
    @Size(min = 1, max = 255, message = "Title length must be between 1 and 255 character.")
    private String title;

    @NotNull(message = "Author is required.")
    @NotEmpty(message = "Author can not be empty.")
    @Size(min = 1, max = 255, message = "Author length must be between 1 and 255 character.")
    private String author;

    @NotNull(message = "Publication year is required.")
    @Digits(integer = 4, fraction = 0, message = "Publication year length must be between 1 and 4 digit.")
    private int publicationYear;

    public Book toBook() {
        return new Book()
                .setIsbn(isbn)
                .setTitle(title)
                .setAuthor(author)
                .setPublicationYear(publicationYear);
    }
}
