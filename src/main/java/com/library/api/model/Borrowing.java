package com.library.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "borrowings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "returned_on", nullable = true)
    @Temporal(TemporalType.DATE)
    private String returnedOn;

    @Column(name = "borrowing_date")
    @Temporal(TemporalType.DATE)
    private String borrowingDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private String returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
}
