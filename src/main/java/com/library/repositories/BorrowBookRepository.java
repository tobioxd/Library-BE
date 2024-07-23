package com.library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.models.BorrowBook;

public interface BorrowBookRepository extends JpaRepository<BorrowBook,Long> {

    Page<BorrowBook> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT b FROM BorrowBook b WHERE b.user.id = :id AND b.isReturned = false ")
    Page<BorrowBook> findBorrowedBooksNotReturnYet(@Param("id") Long id, Pageable pageable);

    @Query("SELECT b FROM BorrowBook b WHERE b.user.id = :id AND b.isReturned = true")
    Page<BorrowBook> findBorrowedBooksReturnAldready(@Param("id") Long id, Pageable pageable);

}
