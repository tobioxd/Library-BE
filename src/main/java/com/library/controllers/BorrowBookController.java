package com.library.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dtos.BorrowBookDTO;
import com.library.models.BorrowBook;
import com.library.responses.BorrowBookListResponse;
import com.library.responses.BorrowBookResponse;
import com.library.services.BookService;
import com.library.services.BorrowBookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/borrowbooks")
@RequiredArgsConstructor

public class BorrowBookController {

    private final BookService bookService;
    private final BorrowBookService borrowBookService;

    @PostMapping("")
    @Operation(summary = "Create Borrow Book")
    public ResponseEntity<?> createBorrowBook(
        @Valid @RequestBody BorrowBookDTO borrowBookDTO,
        BindingResult result){
        try {

            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            BorrowBook borrowBook = borrowBookService.createBorrowBook(borrowBookDTO);
            bookService.updateBook(borrowBookDTO.getBookId(), true);

            return ResponseEntity.ok(BorrowBookResponse.builder()
                    .borrowbook(borrowBook)
                    .build());
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get Borrow Book List")
    public ResponseEntity<?> getAllBooks(
        @RequestParam("userId") Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<BorrowBook> borrowBooks = borrowBookService.getAllBorrowedBook(userId, pageRequest);

            int totalPages = borrowBooks.getTotalPages();
            List<BorrowBook> borrowBookResponse = borrowBooks.getContent();

            return ResponseEntity.ok(BorrowBookListResponse.builder()
                    .borrowbooks(borrowBookResponse)
                    .totalPages(totalPages)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail/{borrowId}")
    @Operation(summary = "Get Borrow Book Detail")
    public ResponseEntity<?> getDetailBook(
        @PathVariable("borrowId") Long borrowId
    ){
        try {

            BorrowBook borrowBook = borrowBookService.getBorrowBookbyId(borrowId);

            return ResponseEntity.ok(BorrowBookResponse.builder()
                    .borrowbook(borrowBook)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/return/{borrowId}")
    @Operation(summary = "Return Book")
    public ResponseEntity<?> ReturnBook(
        @PathVariable("borrowId") Long borrowId
    ){
        try {
            
            BorrowBook borrowBook = borrowBookService.updateBorrowBook(borrowId);
            bookService.updateBook(borrowBook.getBook().getId(), false);

            return ResponseEntity.ok(BorrowBookResponse.builder()
                    .borrowbook(borrowBook)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/returned")
    @Operation(summary = "Get Return Borrow Book List")
    public ResponseEntity<?> getAllReturnedBooks(
        @RequestParam("userId") Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<BorrowBook> borrowBooks = borrowBookService.getAllBorrowedBookReturnAldready(userId, pageRequest);

            int totalPages = borrowBooks.getTotalPages();
            List<BorrowBook> borrowBookResponse = borrowBooks.getContent();

            return ResponseEntity.ok(BorrowBookListResponse.builder()
                    .borrowbooks(borrowBookResponse)
                    .totalPages(totalPages)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/notreturn")
    @Operation(summary = "Get Borrow Book Not Return List")
    public ResponseEntity<?> getAllBookNotReturnYet(
        @RequestParam("userId") Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<BorrowBook> borrowBooks = borrowBookService.getAllBorrowedBookNotReturnYet(userId, pageRequest);

            int totalPages = borrowBooks.getTotalPages();
            List<BorrowBook> borrowBookResponse = borrowBooks.getContent();

            return ResponseEntity.ok(BorrowBookListResponse.builder()
                    .borrowbooks(borrowBookResponse)
                    .totalPages(totalPages)
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
