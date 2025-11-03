package com.getom.vinylhub.domains.user;

import java.time.Instant;

public record UserResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        Instant createdAt,
        Instant updatedAt
) {
}
