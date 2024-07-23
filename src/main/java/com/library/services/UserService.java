package com.library.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.models.User;
import com.library.repositories.UserRepository;
import com.library.services.interfaces.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService implements IUserService{
    
    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) throws Exception {
       return userRepository.findAll(pageable);
    }

}
