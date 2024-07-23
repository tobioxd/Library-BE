package com.library.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.models.User;

public interface IUserService {

    Page<User> findAll(Pageable pageable) throws Exception;

}
