DROP TABLE borrow_books;

DROP TABLE books;

DROP TABLE users;

CREATE TABLE users(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    phone_number NVARCHAR2(40) NOT NULL ,
    fullname NVARCHAR2(255) DEFAULT '',
    PRIMARY KEY (id),
    CONSTRAINT phone_number UNIQUE (phone_number)
);

INSERT INTO users(phone_number,fullname) VALUES ('1234567891','User 1');
INSERT INTO users(phone_number,fullname) VALUES ('1234567892','User 2');
INSERT INTO users(phone_number,fullname) VALUES ('1234567893','User 3');
INSERT INTO users(phone_number,fullname) VALUES ('1234567894','User 4');
INSERT INTO users(phone_number,fullname) VALUES ('1234567895','User 5');
INSERT INTO users(phone_number,fullname) VALUES ('1234567896','User 6');

CREATE TABLE books(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    name NVARCHAR2(255) NOT NULL ,
    author NVARCHAR2(255) NOT NULL ,
    publishing_year NUMBER,
    image_url NVARCHAR2(255) NOT NULL ,
    is_borrowed NUMBER(1) DEFAULT 0,
    PRIMARY KEY (id)
);

INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Secret Garden', 'Frances Hodgson Burnett', 1911, 'https://example.com/secret_garden.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Count of Monte Cristo', 'Alexandre Dumas', 1844, 'https://example.com/monte_cristo.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Scarlet Letter', 'Nathaniel Hawthorne', 1850, 'https://example.com/scarlet_letter.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Brothers Karamazov', 'Fyodor Dostoevsky', 1880, 'https://example.com/brothers_karamazov.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Picture of Dorian Gray', 'Oscar Wilde', 1890, 'https://example.com/dorian_gray.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Wind-Up Bird Chronicle', 'Haruki Murakami', 1994, 'https://example.com/wind_up_bird.jpg', 1);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Name of the Wind', 'Patrick Rothfuss', 2007, 'https://example.com/name_of_the_wind.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Road', 'Cormac McCarthy', 2006, 'https://example.com/the_road.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Book Thief', 'Markus Zusak', 2005, 'https://example.com/book_thief.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Martian', 'Andy Weir', 2011, 'https://example.com/martian.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Handmaid''s Tale', 'Margaret Atwood', 1985, 'https://example.com/handmaids_tale.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 1925, 'https://example.com/gatsby.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('Dune', 'Frank Herbert', 1965, 'https://example.com/dune.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Hobbit', 'J.R.R. Tolkien', 1937, 'https://example.com/hobbit.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Alchemist', 'Paulo Coelho', 1988, 'https://example.com/alchemist.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Martian', 'Andy Weir', 2011, 'https://example.com/martian.jpg', 0);
INSERT INTO books (name, author, publishing_year, image_url, is_borrowed) VALUES
('The Handmaid''s Tale', 'Margaret Atwood', 1985, 'https://example.com/handmaids.jpg', 0);

CREATE TABLE borrow_books(
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    user_id NUMBER,
    book_id NUMBER,
    phone_number VARCHAR2(255) NOT NULL,
    fullname NVARCHAR2(255) DEFAULT '',
    note VARCHAR2(255) DEFAULT '',
    borrowed_date DATE DEFAULT SYSDATE,
    expired_date DATE,
    is_returned NUMBER(1) DEFAULT 0,
    returned_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(1,1,'1234567891','User 1','');
INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(2,2,'1234567892','User 2','');
INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(3,3,'1234567893','User 3','');
INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(4,4,'1234567894','User 4','');
INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(5,5,'1234567895','User 5','');
INSERT INTO borrow_books(user_id,book_id,phone_number,fullname,note) VALUES(6,6,'1234567896','User 6','');


