package com.library.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.models.Book;
import com.library.responses.BookListResponse;
import com.library.responses.BookResponse;
import com.library.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/books")
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;

    @GetMapping("")
    @Operation(summary = "Get Book List")
    public ResponseEntity<?> getAllBook(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<Book> books = bookService.findAll(keyword, pageRequest);

            int totalPages = books.getTotalPages();
            List<Book> bookResponse = books.getContent();

            return ResponseEntity.ok(BookListResponse.builder()
                    .books(bookResponse)
                    .totalPages(totalPages)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ready")
    @Operation(summary = "Get Ready Book List")
    public ResponseEntity<?> getAllReadyBook(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<Book> books = bookService.getAllReadyBook(keyword, pageRequest);

            int totalPages = books.getTotalPages();
            List<Book> bookResponse = books.getContent();

            return ResponseEntity.ok(BookListResponse.builder()
                    .books(bookResponse)
                    .totalPages(totalPages)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "Get Book Detail")
    public ResponseEntity<?> getDetailBook(
        @PathVariable("id") Long bookId
    ){
        try {

            Book book = bookService.getBookById(bookId);

            return ResponseEntity.ok(BookResponse.builder()
                    .book(book)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
