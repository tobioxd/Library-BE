package com.library.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.models.User;
import com.library.responses.UserResponse;
import com.library.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "Get User List")
    public ResponseEntity<?> getAllUser(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int limit
    ){
        try {

            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

            Page<User> users = userService.findAll(pageRequest);

            int totalPages = users.getTotalPages();
            List<User> userResponse = users.getContent();

            return ResponseEntity.ok(UserResponse.builder()
                    .users(userResponse)
                    .totalPages(totalPages)
                    .build());
    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
