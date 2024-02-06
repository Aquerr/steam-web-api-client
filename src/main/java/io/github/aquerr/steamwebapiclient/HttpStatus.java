package io.github.aquerr.steamwebapiclient;

import java.util.Arrays;

enum HttpStatus {
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    TOO_MANY_REQUESTS(429),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static HttpStatus findByCode(int code)
    {
        return Arrays.stream(values())
                .filter(httpStatus -> httpStatus.getCode() == code)
                .findFirst()
                .orElse(null);
    }
}
