package com.project.bookreview.dto;

import com.project.bookreview.entity.CategoryEnum;
import com.project.bookreview.entity.LanguageEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String title;

    private String author;

    private CategoryEnum category;

    private double price;

    private LocalDate publishedDate;

    private int pageCount;

    private LanguageEnum language;
}
