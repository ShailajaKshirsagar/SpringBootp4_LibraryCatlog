package com.catlog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//This is for one to one mapping
@Setter
@Getter
@ToString
@Entity
public class BookDetailsMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String language;
    private int pages;

}
