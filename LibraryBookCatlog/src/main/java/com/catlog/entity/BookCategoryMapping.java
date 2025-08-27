package com.catlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

//class for many to many mapping
@Entity
@Getter
@Setter
@ToString
public class BookCategoryMapping {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private int id;

        private String name;

        @ManyToMany(mappedBy = "categories")
        @JsonIgnore
        private Set<Book> books = new HashSet<>();
}
