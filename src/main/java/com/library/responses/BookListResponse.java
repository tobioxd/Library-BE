package com.library.responses;

import java.util.List;

import com.library.models.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class BookListResponse {

    private List<Book> books;

    private int totalPages;

}
