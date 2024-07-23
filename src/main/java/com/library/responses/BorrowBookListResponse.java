package com.library.responses;

import java.util.List;

import com.library.models.BorrowBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class BorrowBookListResponse {

    private List<BorrowBook> borrowbooks;

    private int totalPages;

}
