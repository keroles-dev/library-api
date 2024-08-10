package com.library.api.model;

import com.library.api.exception.ResourceAlreadyExistsException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // You need 17 characters for ISBN13, 13 numbers plus the hyphens,
    // and 13 characters for ISBN10, 10 numbers plus hyphens.
    @Column(name = "isbn", nullable = false, unique = true, length = 17)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "author", nullable = false)
    private String author;


    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
//    private Set<Borrowing> borrowingSet;
}
