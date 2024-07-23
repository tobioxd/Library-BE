package com.library.repositories;

import org.springframework.stereotype.Repository;
import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    boolean existsByPhoneNumber(String phonenumber);

    Optional<User> findByPhoneNumber(String phonenumber);

}
