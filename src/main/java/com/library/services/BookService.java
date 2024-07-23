package com.library.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.exceptions.DataNotFoundException;
import com.library.models.Book;
import com.library.repositories.BookRepository;
import com.library.services.interfaces.IBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    @Override
    public boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }

    @Override
    public Book getBookById(long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not file book with id :" + id));
    }

    @Override
    public Page<Book> getAllReadyBook(String keyword, Pageable pageable) throws Exception {
        return bookRepository.findReadyBooks(keyword,pageable);
    }

    @Override
    public Book updateBook(long id, boolean isBorrowed) throws Exception {
        
        Book existingBook = getBookById(id);
        if(existingBook != null){

            existingBook.setBorrowed(isBorrowed);
            return bookRepository.save(existingBook);

        }
        return null;

    }

    @Override
    public Page<Book> findAll(String keyword, Pageable pageable) throws Exception {
        return bookRepository.findAll(keyword, pageable);
    }

}
