package com.getom.vinylhub.domains.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank(message = "First name is required")
        @Size(max = 255, message = "First name must be less than 255 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 255, message = "Last name must be less than 255 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Size(max = 255, message = "Email must be less than 255 characters")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 255, message = "Password must be at least 8 and less than 255 characters")
        String password,

        UserRole role
) {
    public UpdateUserRequest {
        if (role == null) {
            role = UserRole.COLLECTOR;
        }
    }
}
