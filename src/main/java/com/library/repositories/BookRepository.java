package com.library.repositories;

import org.springframework.stereotype.Repository;
import com.library.models.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByName(String name);

    @Query("SELECT b FROM Book b WHERE (:keyword IS NULL OR :keyword ='' OR b.name LIKE %:keyword% OR b.author LIKE %:keyword%)")
    Page<Book> findAll(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isBorrowed = false " +
            "AND (:keyword IS NULL OR :keyword ='' OR b.name LIKE %:keyword% OR b.author LIKE %:keyword%)")
    Page<Book> findReadyBooks(@Param("keyword") String keyword, Pageable pageable);

}
