package com.library.responses;

import java.util.List;

import com.library.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class UserResponse {

    private List<User> users;

    private int totalPages;

}
