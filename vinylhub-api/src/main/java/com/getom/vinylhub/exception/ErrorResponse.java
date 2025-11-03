package com.getom.vinylhub.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        String error,
        int statusCode,
        String path,
        Instant timestamp,
        Map<String, String> errors
) {
}
