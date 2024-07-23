package com.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40,nullable = false)
    private String name;

    @Column(name = "author", length = 40,nullable = false)
    private String author;

    @Column(name = "publishing_year")
    private int publishingYear;

    @Column(name = "image_url", length = 40,nullable = false)
    private String imageUrl;

    @Column(name = "is_borrowed")
    private boolean isBorrowed;
    
}
