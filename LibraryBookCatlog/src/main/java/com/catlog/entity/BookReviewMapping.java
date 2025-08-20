package com.catlog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class BookReviewMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String reviewerName;
    private int rating;

    // Many reviews belong to one book
    @ManyToOne
    @JoinColumn(name = "book_id") // foreign key in Review table
    private Book book;

}
