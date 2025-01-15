package com.project.bookreview.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book", schema="bookreview")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private double price;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "page_count")
    private int pageCount;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Review> reviews;



}
