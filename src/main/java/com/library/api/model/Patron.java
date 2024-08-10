package com.library.api.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "patrons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_info", columnDefinition = "json", nullable = false, length = 500)
    @JsonRawValue
    private String contactInfo;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patron")
//    private Set<Borrowing> borrowingSet;
}
