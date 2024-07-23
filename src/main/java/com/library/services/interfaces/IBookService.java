package com.library.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.models.Book;

public interface IBookService {

    boolean existsByName(String name);

    Book getBookById(long id) throws Exception;

    Page<Book> findAll(String keyword, Pageable pageable) throws Exception;

    Page<Book> getAllReadyBook(String keyword, Pageable pageable) throws Exception;

    Book updateBook(long id, boolean isBorrowed) throws Exception;

}
