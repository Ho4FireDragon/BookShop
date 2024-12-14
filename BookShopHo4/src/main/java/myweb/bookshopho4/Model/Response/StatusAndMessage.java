package myweb.bookshopho4.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public enum StatusAndMessage {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Invalid request"),
    EMAIL_EXISTS(401, "Email already exists"),
    NOT_FOUND(404, "Resource not found"),
    SERVER_ERROR(500, "Internal server error"),
    UNAUTHORIZED(401, "Unauthorized access"),
    UNAUTHENTICATED(401, "Wrong password or email"),
    VALIDTOKEN(402,"Valid token"),
    INVALIDTOKEN(401,"Invalid token");


    private final int code;
    private final String message;

}

