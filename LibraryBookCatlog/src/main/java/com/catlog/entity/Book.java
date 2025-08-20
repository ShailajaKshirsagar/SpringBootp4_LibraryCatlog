package com.catlog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//entity class
@Entity
@Setter
@Getter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String name;
    private String author;
    private String isbn;
    private int availableCopies;

    //one to one mapped with Details
    @OneToOne(cascade = CascadeType.ALL)        //automatically save the data
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private BookDetailsMapping detail;

    //one book many reviews one to many
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReviewMapping> reviews = new ArrayList<>();
}
