package com.library.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BorrowBookDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    @JsonProperty("book_id")
    @Min(value = 1, message = "Book's ID must be > 0")
    private Long bookId;

    private String fullname;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 8,max = 10, message = "Phone number must be between 8 and 10 characters")
    private String phoneNumber;

    private String note;

    @NotNull(message = "expired time is required")
    private int expired;

}
