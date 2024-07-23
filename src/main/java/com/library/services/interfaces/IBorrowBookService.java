package com.library.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.dtos.BorrowBookDTO;
import com.library.exceptions.DataNotFoundException;
import com.library.models.BorrowBook;

public interface IBorrowBookService {

    BorrowBook createBorrowBook(BorrowBookDTO borrowBookDTO) throws Exception;

    Page<BorrowBook> getAllBorrowedBook(Long userId,Pageable pageable) throws Exception;

    BorrowBook getBorrowBookbyId(Long id) throws DataNotFoundException;

    BorrowBook updateBorrowBook(Long id) throws DataNotFoundException;

    Page<BorrowBook> getAllBorrowedBookNotReturnYet(Long id,Pageable pageable) throws DataNotFoundException;

    Page<BorrowBook> getAllBorrowedBookReturnAldready(Long id,Pageable pageable) throws DataNotFoundException;
}
