package com.library.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "borrow_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BorrowBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "phone_number", length = 40,nullable = false)
    private String phoneNumber;

    @Column(name = "fullname",length = 255)
    private String fullName;

    @Column(name = "note",length = 255)
    private String note;

    @Column(name ="borrowed_date")
    private Date borrowedDate;

    @Column(name ="expired_date")
    private Date expiredDate;

    @Column(name = "is_returned")
    private boolean isReturned;

    @Column(name ="returned_date")
    private Date returnedDate;

}
