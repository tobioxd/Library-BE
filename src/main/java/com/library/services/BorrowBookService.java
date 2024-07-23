package com.library.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.dtos.BorrowBookDTO;
import com.library.exceptions.BookNotReadyException;
import com.library.exceptions.DataNotFoundException;
import com.library.models.Book;
import com.library.models.BorrowBook;
import com.library.models.User;
import com.library.repositories.BookRepository;
import com.library.repositories.BorrowBookRepository;
import com.library.repositories.UserRepository;
import com.library.services.interfaces.IBorrowBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BorrowBookService implements IBorrowBookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowBookRepository borrowBookRepository;

    @Override
    public BorrowBook createBorrowBook(BorrowBookDTO borrowBookDTO) throws Exception {

        User user = userRepository.findById(borrowBookDTO.getUserId()).orElseThrow(
                () -> new DataNotFoundException("Couldnt find user with id: " + borrowBookDTO.getUserId()));

        Book book = bookRepository.findById(borrowBookDTO.getBookId()).orElseThrow(
                () -> new DataNotFoundException("Couldnt find book with id: " + borrowBookDTO.getBookId()));

        if (book.isBorrowed()) {
            throw new BookNotReadyException("This book is borrowed already !");
        }

        BorrowBook newBorrowBook = BorrowBook.builder()
                .user(user)
                .book(book)
                .phoneNumber(borrowBookDTO.getPhoneNumber())
                .fullName(borrowBookDTO.getFullname())
                .note(borrowBookDTO.getNote())
                .borrowedDate(new Date())
                .expiredDate(addDaysToDate(new Date(), borrowBookDTO.getExpired()))
                .isReturned(false)
                .build();

        return borrowBookRepository.save(newBorrowBook);
    }

    @Override
    public Page<BorrowBook> getAllBorrowedBook(Long userId, Pageable pageable) throws Exception {
        return borrowBookRepository.findByUserId(userId, pageable);
    }

    @Override
    public BorrowBook getBorrowBookbyId(Long id) throws DataNotFoundException {
        return borrowBookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(" Couldnt find borrow with the id: " + id));
    }

    @Override
    public BorrowBook updateBorrowBook(Long id) throws DataNotFoundException {

        BorrowBook borrowBook = borrowBookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(" Couldnt find borrow with the id: " + id));

        if(borrowBook.isReturned()){
            throw new DataNotFoundException("This book have return already");
        }

        borrowBook.setReturned(true);
        borrowBook.setReturnedDate(new Date());

        return borrowBookRepository.save(borrowBook);

    }

    private static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    @Override
    public Page<BorrowBook> getAllBorrowedBookNotReturnYet(Long id, Pageable pageable) throws DataNotFoundException {

        Page<BorrowBook> books = borrowBookRepository.findBorrowedBooksNotReturnYet(id, pageable);

        if (books.isEmpty()) {
            throw new DataNotFoundException("No book found !");
        }

        return books;

    }

    @Override
    public Page<BorrowBook> getAllBorrowedBookReturnAldready(Long id, Pageable pageable) throws DataNotFoundException {

        Page<BorrowBook> books = borrowBookRepository.findBorrowedBooksReturnAldready(id, pageable);

        if (books.isEmpty()) {
            throw new DataNotFoundException("No book found !");
        }

        return books;

    }

    
}
